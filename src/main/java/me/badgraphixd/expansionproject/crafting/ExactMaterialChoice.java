package me.badgraphixd.expansionproject.crafting;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class ExactMaterialChoice extends RecipeChoice.ExactChoice {

    public ExactMaterialChoice(@NotNull Material... choices) {
        super(Arrays.stream(choices).map(ItemStack::new).collect(Collectors.toList()));
    }
}
