package me.badgraphixd.expansionproject.player;

import me.badgraphixd.expansionproject.magic.mana.FiniteManaContainer;
import me.badgraphixd.expansionproject.role.Profile;
import me.badgraphixd.expansionproject.skill.SkillSet;
import org.bson.Document;

import java.util.UUID;

public class PlayerData {

    private final UUID uuid;

    private final SkillSet skillSet;
    private final FiniteManaContainer manaContainer;
    private final Profile profile;

    // Stats (deaths, kills, damage dealt)
    // Achievements
    // Active effects
    // Pets
    // Relationships

    public UUID getUuid() {
        return uuid;
    }
    public SkillSet getSkillSet() {
        return skillSet;
    }
    public FiniteManaContainer getManaContainer() {
        return manaContainer;
    }
    public Profile getProfile() {
        return profile;
    }

    public PlayerData(UUID uuid, SkillSet skillSet, FiniteManaContainer manaContainer, Profile profile) {
        this.uuid = uuid;
        this.skillSet = skillSet;
        this.manaContainer = manaContainer;
        this.profile = profile;
    }

    public PlayerData(Document document) {
        this(
            UUID.fromString(document.getString("uuid")),
            new SkillSet((Document) document.get("skill_set")),
            new FiniteManaContainer((Document) document.get("mana_container")),
            new Profile((Document) document.get("profile"))
        );
    }

    public Document toDocument() {
        return new Document()
            .append("uuid", uuid.toString())
            .append("skill_set", skillSet.toDocument())
            .append("mana_container", manaContainer.toDocument())
            .append("profile", profile.toDocument());
    }

}
