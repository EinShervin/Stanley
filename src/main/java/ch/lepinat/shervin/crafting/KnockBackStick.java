package ch.lepinat.shervin.crafting;

import ch.lepinat.shervin.helper.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class KnockBackStick {
    public void registerRecipes() {
        ShapedRecipe knockbackStickRecipe = new ShapedRecipe(NamespacedKey.minecraft("knockbackstick"), Items.getKnockBackStick());
        knockbackStickRecipe.shape("BNZ", "ASA", "DLD");
        knockbackStickRecipe.setIngredient('B', Material.DIAMOND_SWORD);
        knockbackStickRecipe.setIngredient('N', Material.NETHER_STAR);
        knockbackStickRecipe.setIngredient('Z', Material.STICKY_PISTON);
        knockbackStickRecipe.setIngredient('A', Material.BLAZE_ROD);
        knockbackStickRecipe.setIngredient('S', Material.STICK);
        knockbackStickRecipe.setIngredient('D', Material.DIAMOND);
        knockbackStickRecipe.setIngredient('L', Material.LAVA_BUCKET);

        Bukkit.addRecipe(knockbackStickRecipe);
    }
}
