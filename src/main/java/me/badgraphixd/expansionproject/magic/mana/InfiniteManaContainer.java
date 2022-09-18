package me.badgraphixd.expansionproject.magic.mana;

import org.bson.Document;

import java.util.EnumMap;
import java.util.Map;

public class InfiniteManaContainer extends ManaContainer {

    private final EnumMap<ManaType, Boolean> mana;

    public InfiniteManaContainer() {
        this.mana = new EnumMap<>(ManaType.class);
        for (ManaType type : ManaType.values()) {
            mana.put(type, false);
        }
        this.drawLimitation = 0;
    }

    public InfiniteManaContainer(EnumMap<ManaType, Boolean> mana) {
        this();
        this.mana.putAll(mana);
    }

    public InfiniteManaContainer(Document document) {
        this();
        for (ManaType type : ManaType.values()) {
            mana.put(type, document.getBoolean(type.name()));
        }
        drawLimitation = document.getInteger("draw_limitation");
    }

    @Override
    public int get(ManaType type) {
        return mana.get(type) ? Integer.MAX_VALUE : 0;
    }

    @Override
    public void subtract(ManaType type, int value) { }

    public Document toDocument() {
        Document document = new Document();

        for (Map.Entry<ManaType, Boolean> entry : mana.entrySet()) {
            document.append(entry.getKey().name(), entry.getValue());
        }

        document.append("draw_limitation", drawLimitation);

        return document;
    }

}
