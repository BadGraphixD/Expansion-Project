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
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

public class PacketManager {

    public static void init(JavaPlugin plugin) {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

        protocolManager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL, PacketType.Play.Client.BLOCK_DIG) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if (event.getPacketType().equals(PacketType.Play.Client.BLOCK_DIG)) {
                    PacketContainer packet = event.getPacket();

                    EnumWrappers.PlayerDigType status = packet.getPlayerDigTypes().read(0);
                    BlockPosition blockPosition = packet.getBlockPositionModifier().read(0);

                    Location location = blockPosition.toLocation(event.getPlayer().getWorld());
                    Block block = location.getBlock();

                    if (BlockManager.isCustomBlock(block)) {
                        switch (status) {
                            case START_DESTROY_BLOCK:
                                BrokenBlockManager.createBrokenBlock(block);
                                break;
                            case ABORT_DESTROY_BLOCK:
                                BrokenBlockManager.removeBrokenBlock(location);
                                break;
                            default:
                                return;
                        }
                        event.setCancelled(true);
                    }
                }
            }
        });
    }

}
