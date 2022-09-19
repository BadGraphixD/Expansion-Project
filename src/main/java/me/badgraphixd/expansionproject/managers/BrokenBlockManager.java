package me.badgraphixd.expansionproject.managers;

import me.badgraphixd.expansionproject.ExpansionProject;
import me.badgraphixd.expansionproject.block.CustomBlock;
import me.badgraphixd.expansionproject.block.PlayerBlockBreakingProcess;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class BrokenBlockManager {

    public enum ToolType {
        SHOVEL,
        AXE,
        PICKAXE,
        HOE
    }

    private enum ToolMaterial {
        WOOD(2f),
        STONE(4f),
        IRON(6f),
        DIAMOND(8f),
        NETHERITE(9f),
        GOLD(12f);

        public final float toolSpeed;

        ToolMaterial(float toolSpeed) {
            this.toolSpeed = toolSpeed;
        }
    }

    private static class ToolProperties {
        public final ToolType type;
        public final ToolMaterial material;

        public ToolProperties(ToolType type, ToolMaterial material) {
            this.type = type;
            this.material = material;
        }
    }

    private static final Map<Material, ToolProperties> tools = new HashMap<>();
    private static final Map<Player, PlayerBlockBreakingProcess> blockBreakingProcesses = new HashMap<>();

    static {
        tools.put(Material.WOODEN_SHOVEL, new ToolProperties(ToolType.SHOVEL, ToolMaterial.WOOD));
        tools.put(Material.STONE_SHOVEL, new ToolProperties(ToolType.SHOVEL, ToolMaterial.STONE));
        tools.put(Material.IRON_SHOVEL, new ToolProperties(ToolType.SHOVEL, ToolMaterial.IRON));
        tools.put(Material.DIAMOND_SHOVEL, new ToolProperties(ToolType.SHOVEL, ToolMaterial.DIAMOND));
        tools.put(Material.NETHERITE_SHOVEL, new ToolProperties(ToolType.SHOVEL, ToolMaterial.NETHERITE));
        tools.put(Material.GOLDEN_SHOVEL, new ToolProperties(ToolType.SHOVEL, ToolMaterial.GOLD));

        tools.put(Material.WOODEN_AXE, new ToolProperties(ToolType.AXE, ToolMaterial.WOOD));
        tools.put(Material.STONE_AXE, new ToolProperties(ToolType.AXE, ToolMaterial.STONE));
        tools.put(Material.IRON_AXE, new ToolProperties(ToolType.AXE, ToolMaterial.IRON));
        tools.put(Material.DIAMOND_AXE, new ToolProperties(ToolType.AXE, ToolMaterial.DIAMOND));
        tools.put(Material.NETHERITE_AXE, new ToolProperties(ToolType.AXE, ToolMaterial.NETHERITE));
        tools.put(Material.GOLDEN_AXE, new ToolProperties(ToolType.AXE, ToolMaterial.GOLD));

        tools.put(Material.WOODEN_PICKAXE, new ToolProperties(ToolType.PICKAXE, ToolMaterial.WOOD));
        tools.put(Material.STONE_PICKAXE, new ToolProperties(ToolType.PICKAXE, ToolMaterial.STONE));
        tools.put(Material.IRON_PICKAXE, new ToolProperties(ToolType.PICKAXE, ToolMaterial.IRON));
        tools.put(Material.DIAMOND_PICKAXE, new ToolProperties(ToolType.PICKAXE, ToolMaterial.DIAMOND));
        tools.put(Material.NETHERITE_PICKAXE, new ToolProperties(ToolType.PICKAXE, ToolMaterial.NETHERITE));
        tools.put(Material.GOLDEN_PICKAXE, new ToolProperties(ToolType.PICKAXE, ToolMaterial.GOLD));

        tools.put(Material.WOODEN_HOE, new ToolProperties(ToolType.HOE, ToolMaterial.WOOD));
        tools.put(Material.STONE_HOE, new ToolProperties(ToolType.HOE, ToolMaterial.STONE));
        tools.put(Material.IRON_HOE, new ToolProperties(ToolType.HOE, ToolMaterial.IRON));
        tools.put(Material.DIAMOND_HOE, new ToolProperties(ToolType.HOE, ToolMaterial.DIAMOND));
        tools.put(Material.NETHERITE_HOE, new ToolProperties(ToolType.HOE, ToolMaterial.NETHERITE));
        tools.put(Material.GOLDEN_HOE, new ToolProperties(ToolType.HOE, ToolMaterial.GOLD));
    }

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
                damageProcess(process, player);
                return;
            }
            stopBreakingBlock(player);
        }

        PlayerBlockBreakingProcess newProcess = new PlayerBlockBreakingProcess(block, getBlockDurability(block));
        blockBreakingProcesses.put(player, newProcess);
        damageProcess(newProcess, player);
    }

    public static void stopBreakingBlock(Player player) {
        PlayerBlockBreakingProcess oldProcess = blockBreakingProcesses.remove(player);
        if (oldProcess != null) {
            sendBreakPacket(oldProcess.getBlock(), -1);
        }
    }

    private static void damageProcess(PlayerBlockBreakingProcess process, Player player) {
        Block block = process.getBlock();
        process.damage(calcDamage(block, player));
        if (process.updateAnimation()) {
            sendBreakPacket(block, process.getAnimation());
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

    private static float calcDamage(Block block, Player player) {
        float damage = 1f;
        ItemStack item = player.getInventory().getItemInMainHand();
        ToolProperties toolProperties = tools.get(item.getType());

        if (toolProperties != null) {
            CustomBlock customBlock = BlockManager.getCustomBlock(block);
            if (customBlock == null) {
                ExpansionProject.error("Breaking invalid custom block");
                return 1f;
            }

            if (customBlock.getToolTypes().contains(toolProperties.type)) {

                float toolSpeed = toolProperties.material.toolSpeed;
                int efficiencyLevel = item.getEnchantmentLevel(Enchantment.DIG_SPEED);
                if (efficiencyLevel > 0) {
                    toolSpeed *= efficiencyLevel * efficiencyLevel + 1;
                }
                damage *= toolSpeed;

                if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
                    int hasteLevel = player.getPotionEffect(PotionEffectType.FAST_DIGGING).getAmplifier() + 1;
                    damage *= 1f + 0.2f * hasteLevel;
                }
            }
            else damage *= 0.3f;
        }
        return damage;
    }

}
