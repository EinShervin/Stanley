package ch.lepinat.shervin.stanley.crafting;

import ch.lepinat.shervin.stanley.helper.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.HashMap;
import java.util.Map;

public class ChairRecipe {

    public void registerRecipes() {
        ItemStack chair = Items.getChair();
        HashMap<String, Material> slabs = Items.getChairMaterials();
        for (Map.Entry map : slabs.entrySet()) {
            ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft((String) map.getKey()), chair);
            recipe.shape("A", "B");
            recipe.setIngredient('A', Material.MINECART);
            recipe.setIngredient('B', (Material) map.getValue());
            Bukkit.addRecipe(recipe);
        }
    }
}
