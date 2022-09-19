package me.badgraphixd.expansionproject.managers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PacketManager {

    public static void init(JavaPlugin plugin) {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

        /*
        protocolManager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL, PacketType.Play.Client.getInstance().values()) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if (event.getPacketType().equals(PacketType.Play.Client.LOOK)) return;
                if (event.getPacketType().equals(PacketType.Play.Client.POSITION)) return;
                if (event.getPacketType().equals(PacketType.Play.Client.POSITION_LOOK)) return;
                System.out.println(event.getPacketType().toString());
            }
        });
        */

        protocolManager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL,
                PacketType.Play.Client.USE_ENTITY,
                PacketType.Play.Client.USE_ITEM) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if (!event.getPacketType().equals(PacketType.Play.Client.USE_ENTITY) &&
                    !event.getPacketType().equals(PacketType.Play.Client.USE_ITEM)) return;

                // Stops the breaking process when the player performs an arm swing in another context
                BrokenBlockManager.stopBreakingBlock(event.getPlayer());
            }
        });

        protocolManager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL,
                PacketType.Play.Client.ARM_ANIMATION) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if (event.getPacketType().equals(PacketType.Play.Client.ARM_ANIMATION)) {
                    Player player = event.getPlayer();
                    Block targetBlock = player.getTargetBlockExact(5, FluidCollisionMode.NEVER);
                    if (targetBlock == null || BlockManager.getCustomBlock(targetBlock) == null) return;

                    BrokenBlockManager.breakBlock(player, targetBlock);
                }
            }
        });

        protocolManager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL,
                PacketType.Play.Client.BLOCK_DIG) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if (!event.getPacketType().equals(PacketType.Play.Client.BLOCK_DIG)) return;

                PacketContainer packet = event.getPacket();
                Player player = event.getPlayer();

                EnumWrappers.PlayerDigType status = packet.getPlayerDigTypes().read(0);
                BlockPosition blockPosition = packet.getBlockPositionModifier().read(0);

                Location location = blockPosition.toLocation(event.getPlayer().getWorld());
                Block block = location.getBlock();

                if (BlockManager.getCustomBlock(block) != null) {
                    switch (status) {
                        case START_DESTROY_BLOCK:
                            BrokenBlockManager.stopBreakingBlock(player);
                            BrokenBlockManager.breakBlock(player, block);
                            break;
                        case ABORT_DESTROY_BLOCK:
                            BrokenBlockManager.stopBreakingBlock(player);
                            break;
                        default:
                            return;
                    }
                    event.setCancelled(true);
                }
            }
        });
    }

}
