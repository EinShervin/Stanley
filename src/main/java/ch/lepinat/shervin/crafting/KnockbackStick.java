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

public class KnockbackStick {

    public void registerRecipes() {
        ItemStack knockBackStick = new ItemStack(Material.STICK);
        ItemMeta meta = knockBackStick.getItemMeta();
        meta.addEnchant(Enchantment.KNOCKBACK, 10, true);
        meta.displayName(Component.text("§f§l§k@§5§l§oKnockback§5§lStick§f§l§k@"));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("§fDer §f§l§k@§5§l§oUltimative§f§l§k@ §fKnockback Stick"));
        meta.lore(lore);
        knockBackStick.setItemMeta(meta);

        ShapedRecipe knockbackStickRecipe = new ShapedRecipe(NamespacedKey.minecraft("knockbackstick"), knockBackStick);
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
