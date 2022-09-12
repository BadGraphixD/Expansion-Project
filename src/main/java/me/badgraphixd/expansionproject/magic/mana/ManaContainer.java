package me.badgraphixd.expansionproject.magic.mana;

import org.bson.Document;

import java.util.EnumMap;
import java.util.Map;

public class ManaContainer {

    private final EnumMap<ManaType, Integer> mana;

    public ManaContainer() {
        this.mana = new EnumMap<>(ManaType.class);
        for (ManaType type : ManaType.values()) {
            mana.put(type, 0);
        }
    }

    public ManaContainer(EnumMap<ManaType, Integer> mana) {
        this();
        this.mana.putAll(mana);
    }

    public ManaContainer(Document document) {
        this();
        for (ManaType type : ManaType.values()) {
            mana.put(type, document.getInteger(type.name()));
        }
    }

    public int get(ManaType type) {
        return mana.get(type);
    }

    public void set(ManaType type, int value) {
        mana.put(type, value);
    }

    public void add(ManaType type, int value) {
        mana.put(type, mana.get(type) + value);
    }

    public void subtract(ManaType type, int value) {
        mana.put(type, Math.max(0, mana.get(type) - value));
    }

    public Document toDocument() {
        Document document = new Document();

        for (Map.Entry<ManaType, Integer> entry : mana.entrySet()) {
            document.append(entry.getKey().name(), entry.getValue());
        }

        return document;
    }

}
