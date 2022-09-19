package me.badgraphixd.expansionproject.block;

import me.badgraphixd.expansionproject.managers.BlockManager;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.MultipleFacing;

public class CustomBlock {

    private final CustomBlockState state;
    private final float hardness;

    public CustomBlock(CustomBlockState state, float hardness) {
        this.state = state;
        this.hardness = hardness;
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

}
