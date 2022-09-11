package me.badgraphixd.expansionproject.player;

import me.badgraphixd.expansionproject.skill.SkillSet;
import org.bson.Document;

import java.util.UUID;

public class PlayerData {

    private final UUID uuid;

    private SkillSet skillSet;
    // Stats (deaths, kills, damage dealt)
    // Achievements
    // Active effects
    // Race/role
    // Pets
    // Relationships

    public SkillSet getSkillSet() {
        return skillSet;
    }
    public UUID getUuid() {
        return uuid;
    }

    public PlayerData(UUID uuid, SkillSet skillSet) {
        this.uuid = uuid;
        this.skillSet = skillSet;
    }

    public PlayerData(Document document) {
        this(
            UUID.fromString(document.getString("uuid")),
            new SkillSet((Document) document.get("skill_set"))
        );
    }

    public Document toDocument() {
        return new Document()
            .append("uuid", uuid.toString())
            .append("skill_set", skillSet.toDocument());
    }

}
