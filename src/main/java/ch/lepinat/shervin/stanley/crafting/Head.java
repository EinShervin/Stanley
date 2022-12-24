package ch.lepinat.shervin.stanley.crafting;

import ch.lepinat.shervin.stanley.helper.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class Head {

    public void registerRecipes() {
        ShapedRecipe headRecipe = new ShapedRecipe(NamespacedKey.minecraft("head"), Items.createHead());
        headRecipe.shape("LZL", "EWE", "LKL");
        headRecipe.setIngredient('L', Material.LEATHER);
        headRecipe.setIngredient('Z', Material.ROTTEN_FLESH);
        headRecipe.setIngredient('E', Material.ENDER_EYE);
        headRecipe.setIngredient('W', Material.WHITE_WOOL);
        headRecipe.setIngredient('K', Material.BONE);

        Bukkit.addRecipe(headRecipe);
    }
}
