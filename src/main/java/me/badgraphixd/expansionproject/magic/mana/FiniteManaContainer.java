package me.badgraphixd.expansionproject.magic.mana;

import org.bson.Document;

import java.util.EnumMap;
import java.util.Map;

public class FiniteManaContainer extends ManaContainer {

    private final EnumMap<ManaType, Integer> mana;
    private int drawSpeed;

    public FiniteManaContainer() {
        this.mana = new EnumMap<>(ManaType.class);
        for (ManaType type : ManaType.values()) {
            mana.put(type, 0);
        }
        this.drawLimitation = 0;
        this.drawSpeed = 0;
    }

    public FiniteManaContainer(EnumMap<ManaType, Integer> mana) {
        this();
        this.mana.putAll(mana);
    }

    public FiniteManaContainer(Document document) {
        this();
        for (ManaType type : ManaType.values()) {
            mana.put(type, document.getInteger(type.name()));
        }
        drawLimitation = document.getInteger("draw_limitation");
        drawSpeed = document.getInteger("draw_speed");
    }

    public int getMaxDrawSpeed(ManaContainer source) {
        if (source.drawLimitation <= 0) return Integer.MAX_VALUE;
        if (drawSpeed <= 0) return 0;
        return drawSpeed / source.drawLimitation;
    }

    public void draw(ManaContainer source, ManaType type) {
        draw(source, type, Integer.MAX_VALUE);
    }

    public void draw(ManaContainer source, ManaType type, int maxTransfer) {
        int maxDrawSpeed = Math.min(maxTransfer, getMaxDrawSpeed(source));
        int transfer = Math.max(0, Math.min(maxDrawSpeed, source.get(type)));

        source.subtract(type, transfer);
        add(type, transfer);
    }

    @Override
    public int get(ManaType type) {
        return mana.get(type);
    }

    public void set(ManaType type, int value) {
        mana.put(type, value);
    }

    public void add(ManaType type, int value) {
        mana.put(type, mana.get(type) + value);
    }

    @Override
    public void subtract(ManaType type, int value) {
        mana.put(type, Math.max(0, mana.get(type) - value));
    }

    public Document toDocument() {
        Document document = new Document();

        for (Map.Entry<ManaType, Integer> entry : mana.entrySet()) {
            document.append(entry.getKey().name(), entry.getValue());
        }

        document.append("draw_limitation", drawLimitation);
        document.append("draw_speed", drawSpeed);

        return document;
    }

}
