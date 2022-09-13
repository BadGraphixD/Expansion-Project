package me.badgraphixd.expansionproject.managers;

import me.badgraphixd.expansionproject.ExpansionProject;
import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;

import java.util.HashMap;
import java.util.Map;

public class CraftingManager {

    private static final Map<String, Recipe> recipeRegistry = new HashMap<>();

    public static void register(Recipe recipe) {
        if (!(recipe instanceof Keyed)) {
            ExpansionProject.error("Registering invalid recipe");
            return;
        }
        NamespacedKey recipeKey = ((Keyed) recipe).getKey();
        String recipeName = recipeKey.getKey();
        if (recipeRegistry.containsKey(recipeName)) {
            ExpansionProject.error("Registering duplicate recipe with name: " + recipeKey);
            return;
        }
        if (!Bukkit.addRecipe(recipe)) {
            ExpansionProject.error("Bukkit rejected recipe with name: " + recipeKey);
            return;
        }
        recipeRegistry.put(recipeName, recipe);
    }

}
