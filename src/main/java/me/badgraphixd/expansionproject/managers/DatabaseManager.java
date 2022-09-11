package me.badgraphixd.expansionproject.managers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class DatabaseManager {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private static MongoCollection<Document> playerDataCollection;

    public static void init(JavaPlugin plugin) {
        mongoClient = MongoClients.create(plugin.getConfig().getString("db-connection-link"));
        database = mongoClient.getDatabase(plugin.getConfig().getString("db-database-name"));
        playerDataCollection = database.getCollection(plugin.getConfig().getString("db-player-data-collection-name"));
    }

    public static Document getPlayerData(UUID uuid) {
        return playerDataCollection.find(new Document("uuid", uuid.toString())).first();
    }

    public static Document updatePlayerData(UUID uuid, Document document) {
        return playerDataCollection.findOneAndReplace(new Document("uuid", uuid.toString()), document);
    }

    public static void createPlayerData(Document document) {
        playerDataCollection.insertOne(document);
    }

    public static void deletePlayerData(UUID uuid) {
        playerDataCollection.deleteMany(new Document("uuid", uuid.toString()));
    }

}
