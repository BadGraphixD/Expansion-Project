package me.badgraphixd.expansionproject.block;

import me.badgraphixd.expansionproject.managers.BrokenBlockManager;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class BrokenBlock {

    private int oldProgress;
    private final int breakTime;
    private double damage = -1;
    private final Block block;

    public BrokenBlock(Block block, int breakTime) {
        this.breakTime = breakTime;
        this.block = block;
    }

    public void incrementDamage(Player player, double damage) {
        if (oldProgress >= 10) return;

        this.damage += damage;
        int progress = calcProgress();

        if (progress > oldProgress) {
            if (progress < 10) {
                sendBreakPacket(progress);
            }
            else {
                breakBlock(player);
            }
        }

        oldProgress = progress;
    }

    public void abortBreaking() {
        sendBreakPacket(-1);
    }

    private int calcProgress() {
        return (int) (damage / breakTime * 11) - 1;
    }

    private void breakBlock(Player player) {
        sendBreakPacket(-1);
        BrokenBlockManager.removeBrokenBlock(block.getLocation());
        // send block break sound
        if (player == null) return;
        ((CraftPlayer) player).getHandle().d.a(getBlockPosition(block));
    }

    private void sendBreakPacket(int progress) {
        ((CraftServer) Bukkit.getServer()).getHandle().a(
                null, block.getX(), block.getY(), block.getZ(), 120,
                ((CraftWorld) block.getLocation().getWorld()).getHandle().ab(),
                new PacketPlayOutBlockBreakAnimation(getBlockEntityId(block), getBlockPosition(block), progress)
        );
    }

    private int getBlockEntityId(Block block) {
        return ((block.getX() & 0xFFF) << 20 | (block.getZ() & 0xFFF) << 8) | (block.getY() & 0xFF);
    }

    private BlockPosition getBlockPosition(Block block) {
        return new BlockPosition(block.getX(), block.getY(), block.getZ());
    }

}
