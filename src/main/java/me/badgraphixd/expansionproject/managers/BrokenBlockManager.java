package me.badgraphixd.expansionproject.managers;

import me.badgraphixd.expansionproject.block.BrokenBlock;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;

public class BrokenBlockManager {

    private static final Map<Location, BrokenBlock> brokenBlocks = new HashMap<>();

    public static void createBrokenBlock(Block block) {
        if (isBrokenBlock(block.getLocation())) return;
        brokenBlocks.put(block.getLocation(), new BrokenBlock(block, (int) (getBlockHardness(block) * 30)));
    }

    public static BrokenBlock getBrokenBlock(Location location) {
        createBrokenBlock(location.getBlock());
        return brokenBlocks.get(location);
    }

    public static void removeBrokenBlock(Location blockLocation) {
        BrokenBlock brokenBlock = brokenBlocks.remove(blockLocation);
        if (brokenBlock != null) brokenBlock.abortBreaking();
    }

    public static boolean isBrokenBlock(Location location) {
        return brokenBlocks.containsKey(location);
    }

    private static double getBlockHardness(Block block) {
        if (BlockManager.isCustomBlock(block)) {
            return BlockManager.getCustomBlock(block).getHardness();
        }
        return block.getType().getHardness();
    }

}
