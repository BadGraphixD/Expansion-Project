package me.badgraphixd.expansionproject.block;

import me.badgraphixd.expansionproject.managers.BlockManager;
import me.badgraphixd.expansionproject.managers.BrokenBlockManager;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.MultipleFacing;

import java.util.Arrays;
import java.util.List;

public class CustomBlock {

    private final CustomBlockState state;
    private final float hardness;
    private final List<BrokenBlockManager.ToolType> toolTypes;

    public CustomBlock(CustomBlockState state, float hardness, BrokenBlockManager.ToolType... toolTypes) {
        this.state = state;
        this.hardness = hardness;
        this.toolTypes = Arrays.asList(toolTypes);
        BlockManager.register(this);
    }

    public void setBlock(Block block) {
        block.setType(state.material);
        MultipleFacing blockData = (MultipleFacing) block.getBlockData();
        blockData.setFace(BlockFace.UP, state.up);
        blockData.setFace(BlockFace.DOWN, state.down);
        blockData.setFace(BlockFace.NORTH, state.north);
        blockData.setFace(BlockFace.SOUTH, state.south);
        blockData.setFace(BlockFace.EAST, state.east);
        blockData.setFace(BlockFace.WEST, state.west);
        block.setBlockData(blockData);
    }

    public CustomBlockState getState() {
        return state;
    }

    public float getHardness() {
        return hardness;
    }

    public List<BrokenBlockManager.ToolType> getToolTypes() {
        return toolTypes;
    }

}
