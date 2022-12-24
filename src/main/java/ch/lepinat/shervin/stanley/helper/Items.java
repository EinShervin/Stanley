package ch.lepinat.shervin.stanley.helper;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Items {

    public static ItemStack getFlySoup() {
        ItemStack flySoup = new ItemStack(Material.POTION, 1);
        ItemMeta meta = flySoup.getItemMeta();
        meta.displayName(Component.text("§6Flyingsoup"));
        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("§fFlieg du Jude"));
        meta.lore(lore);
        flySoup.setItemMeta(meta);
        return flySoup;
    }

    public static ItemStack createSkull(@NotNull String name) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(name);
        skullMeta.setOwningPlayer(offlinePlayer);
        head.setItemMeta(skullMeta);
        return head;
    }

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

    public static HashMap<String, Material> getChairMaterials() {
        return new HashMap<>() {{
            put("oak_chair", Material.OAK_SLAB);
            put("spruce_chair", Material.SPRUCE_SLAB);
            put("birch_chair", Material.BIRCH_SLAB);
            put("jungle_chair", Material.JUNGLE_SLAB);
            put("acacia_chair", Material.ACACIA_SLAB);
            put("darkoak_chair", Material.DARK_OAK_SLAB);
        }};
    }

    public static ItemStack createHead() {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = head.getItemMeta();
        meta.displayName(Component.text("§6Magischer Kopf"));
        head.setItemMeta(meta);
        return head;
    }

    public static ItemStack getKnockBackStick() {
        ItemStack knockBackStick = new ItemStack(Material.STICK);
        ItemMeta meta = knockBackStick.getItemMeta();
        meta.addEnchant(Enchantment.KNOCKBACK, 10, true);
        meta.displayName(Component.text("§f§l§k@§5§l§oKnockback§5§lStick§f§l§k@"));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("§fDer §f§l§k@§5§l§oUltimative§f§l§k@ §fKnockback Stick"));
        meta.lore(lore);
        knockBackStick.setItemMeta(meta);
        return knockBackStick;
    }
}
