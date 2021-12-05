package ch.lepinat.shervin.crafting;

import java.util.ArrayList;
import java.util.List;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class FlySoup {

    public void registerRecipes() {
        ItemStack flySoup = new ItemStack(Material.POTION, 1);
        ItemMeta meta = flySoup.getItemMeta();
        meta.displayName(Component.text("§6Flyingsoup"));
        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("§fFlieg du Jude"));
        meta.lore(lore);
        flySoup.setItemMeta(meta);

        ShapedRecipe flySoupRecipe = new ShapedRecipe(NamespacedKey.minecraft("flysoup"), flySoup);
        flySoupRecipe.shape("DPD", "FSF", "DED");
        flySoupRecipe.setIngredient('D', Material.DIAMOND_BLOCK);
        flySoupRecipe.setIngredient('P', Material.PHANTOM_MEMBRANE);
        flySoupRecipe.setIngredient('F', Material.FEATHER);
        flySoupRecipe.setIngredient('S', Material.MUSHROOM_STEW);
        flySoupRecipe.setIngredient('E', Material.FIREWORK_ROCKET);

        Bukkit.addRecipe(flySoupRecipe);
    }

}
