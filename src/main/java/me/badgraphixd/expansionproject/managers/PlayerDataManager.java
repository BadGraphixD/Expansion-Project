package me.badgraphixd.expansionproject.managers;

import me.badgraphixd.expansionproject.ExpansionProject;
import me.badgraphixd.expansionproject.player.PlayerData;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayerDataManager {

    private static int PLAYER_DATA_UNLOAD_TIME = 300;

    private static class CachedPlayerData {

        public PlayerData data;
        public Date lastOnline;
        public boolean online;

        public CachedPlayerData(PlayerData data, Date lastOnline, boolean online) {
            this.data = data;
            this.lastOnline = lastOnline;
            this.online = online;
        }

    }

    private static final List<CachedPlayerData> playerDataCache = new ArrayList<>();

    public static void init(JavaPlugin plugin) {
        PLAYER_DATA_UNLOAD_TIME = plugin.getConfig().getInt("player-data-unload-time");

        for (Player player : Bukkit.getOnlinePlayers()) {
            loadPlayerData(player);
        }
    }

    public static void tick() {
        Date currentDate = new Date();
        Iterator<CachedPlayerData> i = playerDataCache.iterator();

        while (i.hasNext()) {
            CachedPlayerData data = i.next();
            if (data.online) continue;

            long diff = TimeUnit.MILLISECONDS.toSeconds(currentDate.getTime() - data.lastOnline.getTime());
            if (diff < PLAYER_DATA_UNLOAD_TIME) continue;

            i.remove();
        }
    }

    public static boolean loadPlayerData(Player player) {
        for (CachedPlayerData data : playerDataCache) {
            if (data.data.getUuid().equals(player.getUniqueId())) {
                data.online = true;
                return true;
            }
        }

        Document document = DatabaseManager.getPlayerData(player.getUniqueId());

        if (document != null) {
            playerDataCache.add(new CachedPlayerData(new PlayerData(document), new Date(), true));
            return true;
        }

        // No player data exists -> new player
        return false;
    }

    public static void setPlayerOffline(Player player) {
        for (CachedPlayerData data : playerDataCache) {
            if (data.data.getUuid().equals(player.getUniqueId())) {
                data.lastOnline = new Date();
                data.online = false;
                return;
            }
        }
        ExpansionProject.error("Tried to mark player as offline who's data is unloaded/non-existing, with uuid: " + player.getUniqueId());
    }

    public static PlayerData getPlayerData(Player player) {
        for (CachedPlayerData data : playerDataCache) {
            if (data.data.getUuid().equals(player.getUniqueId())) {
                return data.data;
            }
        }
        ExpansionProject.error("Tried to retrieve unloaded/non-existing player data of player with uuid: " + player.getUniqueId());
        return null;
    }

    public static void setPlayerData(Player player, PlayerData data) {
        for (CachedPlayerData cachedPlayerData : playerDataCache) {
            if (player.getUniqueId().equals(cachedPlayerData.data.getUuid())) {
                cachedPlayerData.data = data;
                return;
            }
        }
        ExpansionProject.error("Tried to update/set unloaded/non-existing player data of player with uuid: " + player.getUniqueId());
    }

}
