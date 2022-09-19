package me.badgraphixd.expansionproject.block;

import org.bukkit.Material;

import java.util.Objects;

public class CustomBlockState {

    public final Material material;
    public final boolean up, down, north, south, east, west;

    public CustomBlockState(Material material, boolean up, boolean down, boolean north, boolean south, boolean east, boolean west) {
        this.material = material;
        this.up = up;
        this.down = down;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomBlockState that = (CustomBlockState) o;
        return up == that.up && down == that.down && north == that.north && south == that.south && east == that.east && west == that.west && material == that.material;
    }

    @Override
    public int hashCode() {
        return Objects.hash(material, up, down, north, south, east, west);
    }

    @Override
    public String toString() {
        return "[" + material.name() + "|" + up + "|" + down + "|" + north + "|" + south + "|" + east + "|" + west + "]";
    }
}
