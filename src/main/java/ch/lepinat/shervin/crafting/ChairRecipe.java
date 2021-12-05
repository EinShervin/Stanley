package ch.lepinat.shervin.crafting;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ChairRecipe {

    public static ItemStack getChair() {
        ItemStack chair = new ItemStack(Material.PUFFERFISH_SPAWN_EGG, 1);
        ItemMeta meta = chair.getItemMeta();
        meta.displayName(Component.text("§bChair diese"));
        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("§fErstelle einen Stuhl§7."));
        lore.add(Component.text("§cFunktioniert bis jetzt nur bei Slaps du huansohn§7."));
        meta.lore(lore);
        chair.setItemMeta(meta);
        return chair;
    }

    public void registerRecipes() {
        ItemStack chair = getChair();
        HashMap<String, Material> slabs = getMaterials();
        for (Map.Entry map: slabs.entrySet()) {
            ShapelessRecipe recipe = new ShapelessRecipe(NamespacedKey.minecraft((String) map.getKey()), chair);
            recipe.addIngredient(1, Material.MINECART);
            recipe.addIngredient(1, (Material) map.getValue());
            Bukkit.addRecipe(recipe);
        }
    }

    public static HashMap<String, Material> getMaterials() {
        return new HashMap<>() {{
            put("oak_chair", Material.OAK_SLAB);
            put("spruce_chair", Material.SPRUCE_SLAB);
            put("birch_chair", Material.BIRCH_SLAB);
            put("jungle_chair", Material.JUNGLE_SLAB);
            put("acacia_chair", Material.ACACIA_SLAB);
            put("darkoak_chair", Material.DARK_OAK_SLAB);
        }};
    }
}
