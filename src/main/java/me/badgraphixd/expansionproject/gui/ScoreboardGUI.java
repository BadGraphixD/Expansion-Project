package me.badgraphixd.expansionproject.gui;

import me.badgraphixd.expansionproject.ExpansionProject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.List;

public class ScoreboardGUI {

    public static void updatePlayerScoreboard(Player player, String title, List<String> content) {

        if (!checkLength(title) || !checkContent(content)) {
            return;
        }

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

        if (player.getScoreboard().equals(scoreboardManager.getMainScoreboard())) {
            player.setScoreboard(scoreboardManager.getNewScoreboard());
        }

        Scoreboard scoreboard = player.getScoreboard();

        String playerUuid = player.getUniqueId().toString();
        Objective objective = scoreboard.getObjective(playerUuid);

        if (objective == null) {
            objective = scoreboard.registerNewObjective(playerUuid, Criteria.DUMMY, title);
        }

        int contentSize = content.size();
        for (int i = 0; i < 15; i++) {
            String entry = new String(new char[i + 1]).replace('\0', ' ');

            if (i < contentSize) {
                String line = content.get(i);
                if (line != null && !line.isEmpty() && !line.equals(" ")) {
                    entry = line;
                }
            }

            replaceScore(objective, 15 - i, entry);
        }

        if (objective.getDisplaySlot() != DisplaySlot.SIDEBAR) {
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }

        player.setScoreboard(scoreboard);

    }

    private static boolean checkContent(List<String> content) {

        if (content == null) {
            ExpansionProject.error("Scoreboard content cannot be null");
            return false;
        }

        if (content.size() > 15) {
            ExpansionProject.error("Scoreboard content cannot contain more than 15 entries");
            return false;
        }

        for (String entry : content) {
            if (!checkLength(entry)) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkLength(String entry) {

        if (entry == null || entry.isEmpty()) {
            return true;
        }

        String entryWithoutColor = ChatColor.stripColor(entry);

        if (entryWithoutColor.length() > 16) {
            ExpansionProject.error("Scoreboard entry cannot more than 16 characters long: \"" + entryWithoutColor + "\"");
            return false;
        }
        return true;
    }

    private static void replaceScore(Objective objective, int score, String entry) {
        String entryWithScore = getEntryFromScore(objective, score);

        if (entryWithScore != null) {
            if (entryWithScore.equals(entry)) return;
            else objective.getScoreboard().resetScores(entryWithScore);
        }
        objective.getScore(entry).setScore(score);
    }

    private static String getEntryFromScore(Objective objective, int score) {
        if (objective == null) return null;
        for (String entry : objective.getScoreboard().getEntries()) {
            Score scoreEntry = objective.getScore(entry);
            if (scoreEntry.getScore() == score) {
                return scoreEntry.getEntry();
            }
        }
        return null;
    }

}
