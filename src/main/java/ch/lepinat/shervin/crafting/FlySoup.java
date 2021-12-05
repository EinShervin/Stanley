package ch.lepinat.shervin.crafting;

import ch.lepinat.shervin.helper.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class FlySoup {

    public void registerRecipes() {
        ShapedRecipe flySoupRecipe = new ShapedRecipe(NamespacedKey.minecraft("flysoup"), Items.getFlySoup());
        flySoupRecipe.shape("DPD", "FSF", "DED");
        flySoupRecipe.setIngredient('D', Material.DIAMOND_BLOCK);
        flySoupRecipe.setIngredient('P', Material.PHANTOM_MEMBRANE);
        flySoupRecipe.setIngredient('F', Material.FEATHER);
        flySoupRecipe.setIngredient('S', Material.MUSHROOM_STEW);
        flySoupRecipe.setIngredient('E', Material.FIREWORK_ROCKET);

        Bukkit.addRecipe(flySoupRecipe);
    }

}
