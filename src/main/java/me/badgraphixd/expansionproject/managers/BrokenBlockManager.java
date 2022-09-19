package me.badgraphixd.expansionproject.managers;

import me.badgraphixd.expansionproject.ExpansionProject;
import me.badgraphixd.expansionproject.block.CustomBlock;
import me.badgraphixd.expansionproject.block.PlayerBlockBreakingProcess;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class BrokenBlockManager {

    private static final Map<Player, PlayerBlockBreakingProcess> blockBreakingProcesses = new HashMap<>();

    public static void tick() {
        blockBreakingProcesses.forEach((player, process) -> {
            if (process.wasDamagedLastTick()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 5, 1, false, false, false));
            }
            process.tick();
        });
        blockBreakingProcesses.entrySet().removeIf(entry -> {
            PlayerBlockBreakingProcess process = entry.getValue();
            if (process.finished()) {
                Player player = entry.getKey();
                ((CraftPlayer) player).getHandle().d.a(getBlockPosition(process.getBlock()));
                stopBreakingBlock(player);
            }
            if (process.timeout()) {
                sendBreakPacket(process.getBlock(), -1);
                return true;
            }
            return false;
        });
    }

    public static void breakBlock(Player player, Block block) {
        PlayerBlockBreakingProcess process = blockBreakingProcesses.get(player);

        if (process != null) {
            if (process.getBlock().equals(block)) {
                damageProcess(process);
                return;
            }
            stopBreakingBlock(player);
        }

        PlayerBlockBreakingProcess newProcess = new PlayerBlockBreakingProcess(block, getBlockDurability(block));
        blockBreakingProcesses.put(player, newProcess);
        damageProcess(newProcess);
    }

    public static void stopBreakingBlock(Player player) {
        PlayerBlockBreakingProcess oldProcess = blockBreakingProcesses.remove(player);
        if (oldProcess != null) {
            sendBreakPacket(oldProcess.getBlock(), -1);
        }
    }

    private static void damageProcess(PlayerBlockBreakingProcess process) {
        process.damage(1); // todo change
        if (process.updateAnimation()) {
            sendBreakPacket(process.getBlock(), process.getAnimation());
        }
    }

    private static float getBlockDurability(Block block) {
        CustomBlock customBlock = BlockManager.getCustomBlock(block);
        if (customBlock == null) {
            ExpansionProject.error("Trying to get hardness of invalid custom block!");
            return 0;
        }
        return customBlock.getHardness() * 30f;
    }

    private static void sendBreakPacket(Block block, int animation) {
        ((CraftServer) Bukkit.getServer()).getHandle().a(
                null, block.getX(), block.getY(), block.getZ(), 120,
                ((CraftWorld) block.getLocation().getWorld()).getHandle().ab(),
                new PacketPlayOutBlockBreakAnimation(getBlockEntityId(block), getBlockPosition(block), animation)
        );
    }

    private static int getBlockEntityId(Block block) {
        return ((block.getX() & 0xFFF) << 20 | (block.getZ() & 0xFFF) << 8) | (block.getY() & 0xFF);
    }

    private static BlockPosition getBlockPosition(Block block) {
        return new BlockPosition(block.getX(), block.getY(), block.getZ());
    }

}
