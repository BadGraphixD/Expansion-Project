package me.badgraphixd.expansionproject.magic.mana;

public abstract class ManaContainer {

    protected int drawLimitation;

    public abstract int get(ManaType type);
    public abstract void subtract(ManaType type, int value);

}
