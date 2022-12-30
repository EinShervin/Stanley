package ch.lepinat.shervin.stanley.crafting;

import ch.lepinat.shervin.stanley.helper.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class LightSource {

    public void registerRecipes() {
        ShapedRecipe LightSourceRecipe = new ShapedRecipe(NamespacedKey.minecraft("lightsource"), Items.getLightSource());
        LightSourceRecipe.shape("ADA", "DFD", "ADA");
        LightSourceRecipe.setIngredient('D', Material.FERMENTED_SPIDER_EYE);
        LightSourceRecipe.setIngredient('F', Material.GLOWSTONE);

        Bukkit.addRecipe(LightSourceRecipe);
    }
}
