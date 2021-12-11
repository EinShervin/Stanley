package ch.lepinat.shervin.crafting;

import ch.lepinat.shervin.helper.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class KnockBackStick {
    public void registerRecipes() {
        ShapedRecipe knockBackStickRecipe = new ShapedRecipe(NamespacedKey.minecraft("knockbackstick"), Items.getKnockBackStick());
        knockBackStickRecipe.shape("BNZ", "ASA", "DLD");
        knockBackStickRecipe.setIngredient('B', Material.DIAMOND_SWORD);
        knockBackStickRecipe.setIngredient('N', Material.NETHER_STAR);
        knockBackStickRecipe.setIngredient('Z', Material.STICKY_PISTON);
        knockBackStickRecipe.setIngredient('A', Material.BLAZE_ROD);
        knockBackStickRecipe.setIngredient('S', Material.STICK);
        knockBackStickRecipe.setIngredient('D', Material.DIAMOND);
        knockBackStickRecipe.setIngredient('L', Material.LAVA_BUCKET);

        Bukkit.addRecipe(knockBackStickRecipe);
    }
}
