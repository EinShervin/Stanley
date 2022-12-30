package ch.lepinat.shervin.stanley.crafting;

import ch.lepinat.shervin.stanley.helper.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class InvisibleItemFrame {

    public void registerRecipes() {
        ShapedRecipe InvisibleItemFrameRecipe = new ShapedRecipe(NamespacedKey.minecraft("invisibleitemframe"), Items.getInvisibleItemFrame());
        InvisibleItemFrameRecipe.shape("ADA", "DFD", "ADA");
        InvisibleItemFrameRecipe.setIngredient('D', Material.FERMENTED_SPIDER_EYE);
        InvisibleItemFrameRecipe.setIngredient('F', Material.ITEM_FRAME);

        Bukkit.addRecipe(InvisibleItemFrameRecipe);
    }
}
