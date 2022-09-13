package me.badgraphixd.expansionproject.crafting;

import me.badgraphixd.expansionproject.item.CustomItem;
import me.badgraphixd.expansionproject.item.CustomItemIdentifier;
import me.badgraphixd.expansionproject.managers.ItemManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ExactMaterialChoice extends RecipeChoice.MaterialChoice {

    public ExactMaterialChoice(@NotNull Material... choices) {
        super(Arrays.asList(choices));
    }

    @Override
    public boolean test(@NotNull ItemStack t) {
        List<Material> choices;
        try {
            Field f = getClass().getSuperclass().getDeclaredField("choices");
            f.setAccessible(true);
            choices = (List<Material>) f.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        CustomItemIdentifier id = CustomItemIdentifier.fromItem(t);
        if (id != null) {

            CustomItem customItem = ItemManager.getCustomItemWithId(id);
            if (customItem != null && customItem.isSeparateItem()) {

                return false;
            }
        }

        for (Material match : choices) {
            if (t.getType() == match) {
                return true;
            }
        }

        return false;
    }

}
