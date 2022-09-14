package me.badgraphixd.expansionproject.managers;

import com.google.common.base.CaseFormat;
import me.badgraphixd.expansionproject.gui.ScoreboardGUI;
import me.badgraphixd.expansionproject.magic.mana.ManaType;
import me.badgraphixd.expansionproject.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class PlayerGUIManager {

    public static void tick() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ScoreboardGUI.updatePlayerScoreboard(player, createScoreboardTitle(), createPlayerContent(player));
        }
    }

    private static String createScoreboardTitle() {
        return "" + ChatColor.GOLD + ChatColor.BOLD + "Expansion";
    }

    private static List<String> createPlayerContent(Player player) {
        PlayerData data = PlayerDataManager.getPlayerData(player);
        return Arrays.asList(
                null,
                "Mana:",
                "" + ChatColor.BLUE + data.getManaContainer().get(ManaType.NORMAL),
                "" + ChatColor.RED + data.getManaContainer().get(ManaType.FIRE),
                "" + ChatColor.DARK_GRAY + data.getManaContainer().get(ManaType.NECROMANTIC),
                null,
                "Race:",
                ChatColor.DARK_PURPLE + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, data.getProfile().getRace().name()),
                null,
                "Role:",
                ChatColor.DARK_GREEN + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, data.getProfile().getRole().name())
        );
    }

}
