package me.badgraphixd.expansionproject.role;

import org.bson.Document;

public class Profile {

    private Race race;
    private Role role;

    public Profile(Race race, Role role) {
        this.race = race;
        this.role = role;
    }

    public Profile(Document document) {
        this(
            Race.valueOf(document.getString("race")),
            Role.valueOf(document.getString("role"))
        );
    }

    public Document toDocument() {
        return new Document()
            .append("race", race.name())
            .append("role", role.name());
    }

    public Race getRace() {
        return race;
    }

    public Role getRole() {
        return role;
    }

}
