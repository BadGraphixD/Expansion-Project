package me.badgraphixd.expansionproject.managers;

import me.badgraphixd.expansionproject.ExpansionProject;
import me.badgraphixd.expansionproject.block.CustomBlock;
import me.badgraphixd.expansionproject.block.CustomBlockState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.MultipleFacing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockManager {

    private static final Map<CustomBlockState, CustomBlock> blockRegistry = new HashMap<>();
    private static final List<Material> correctMaterials = Arrays.asList(
            Material.BROWN_MUSHROOM_BLOCK,
            Material.RED_MUSHROOM_BLOCK,
            Material.MUSHROOM_STEM
    );

    public static void register(CustomBlock block) {
        CustomBlockState state = block.getState();
        if (state == null) {
            ExpansionProject.error("Registering block without state");
            return;
        }
        if (blockRegistry.containsKey(state)) {
            ExpansionProject.error("Registering duplicate block state: " + state);
            return;
        }
        blockRegistry.put(state, block);
    }

    public static CustomBlock getCustomBlock(Block block) {
        if (!correctMaterials.contains(block.getType())) return null;
        MultipleFacing blockData = (MultipleFacing) block.getBlockData();
        CustomBlockState customBlockData = new CustomBlockState(
                block.getType(),
                blockData.hasFace(BlockFace.UP),
                blockData.hasFace(BlockFace.DOWN),
                blockData.hasFace(BlockFace.NORTH),
                blockData.hasFace(BlockFace.SOUTH),
                blockData.hasFace(BlockFace.EAST),
                blockData.hasFace(BlockFace.WEST)
        );

        return blockRegistry.get(customBlockData);
    }

}
