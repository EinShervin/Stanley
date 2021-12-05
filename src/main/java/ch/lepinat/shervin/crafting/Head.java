package ch.lepinat.shervin.crafting;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Head {

    public void registerRecipes() {
        ShapedRecipe headRecipe = new ShapedRecipe(NamespacedKey.minecraft("head"), createHead());
        headRecipe.shape("LZL", "EWE", "LKL");
        headRecipe.setIngredient('L', Material.LEATHER);
        headRecipe.setIngredient('Z', Material.ROTTEN_FLESH);
        headRecipe.setIngredient('E', Material.ENDER_EYE);
        headRecipe.setIngredient('W', Material.WHITE_WOOL);
        headRecipe.setIngredient('K', Material.BONE);

        Bukkit.addRecipe(headRecipe);
    }

    public static ItemStack createHead() {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = head.getItemMeta();
        meta.displayName(Component.text("§6Magischer Kopf"));
        head.setItemMeta(meta);
        return head;
    }

}
