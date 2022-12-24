package ch.lepinat.shervin.stanley.crafting;

import ch.lepinat.shervin.stanley.helper.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.HashMap;
import java.util.Map;

public class ChairRecipe {

    public void registerRecipes() {
        ItemStack chair = Items.getChair();
        HashMap<String, Material> slabs = Items.getChairMaterials();
        for (Map.Entry map : slabs.entrySet()) {
            ShapelessRecipe recipe = new ShapelessRecipe(NamespacedKey.minecraft((String) map.getKey()), chair);
            recipe.addIngredient(1, Material.MINECART);
            recipe.addIngredient(1, (Material) map.getValue());
            Bukkit.addRecipe(recipe);
        }
    }
}
