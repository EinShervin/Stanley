package ch.lepinat.shervin.stanley.listener;

import ch.lepinat.shervin.stanley.helper.Items;
import ch.lepinat.shervin.stanley.main.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

public class KnockbackListener implements Listener {

    @EventHandler
    public void onCustomCraftClick(InventoryClickEvent e) {

        if (e.getWhoClicked() instanceof Player) {
            if (e.getClickedInventory() != null && e.getClickedInventory() instanceof CraftingInventory) {
                if (e.getInventory().equals(e.getView().getTopInventory())) {
                    CraftingInventory inv = (CraftingInventory) e.getClickedInventory();
                    if (inv.getItem(1) != null && inv.getItem(1).getType() == Material.ENCHANTED_BOOK) {
                        ItemStack enchantedBook = inv.getItem(1);
                        if (enchantedBook.hasItemMeta() && enchantedBook.getItemMeta() instanceof EnchantmentStorageMeta enchantedMeta) {
                            if (enchantedMeta.getStoredEnchants().size() == 1 && enchantedMeta.hasStoredEnchant(Enchantment.KNOCKBACK)
                                && enchantedMeta.getStoredEnchantLevel(Enchantment.KNOCKBACK) == 2) {
                                if (inv.getItem(2) != null && inv.getItem(2).getType() == Material.NETHER_STAR) {
                                    if (inv.getItem(3) != null && inv.getItem(3).getType() == Material.POTION) {
                                        ItemStack strengthPotion = inv.getItem(3);
                                        if (strengthPotion.hasItemMeta() && strengthPotion.getItemMeta() instanceof PotionMeta potionMeta) {
                                            if (potionMeta.getBasePotionData().getType() == PotionType.STRENGTH && potionMeta.getBasePotionData()
                                                .isUpgraded()) {
                                                if (inv.getItem(4) != null && inv.getItem(4).getType() == Material.BLAZE_ROD) {
                                                    if (inv.getItem(5) != null && inv.getItem(5).getType() == Material.STICK) {
                                                        if (inv.getItem(6) != null && inv.getItem(6).getType() == Material.BLAZE_ROD) {
                                                            if (inv.getItem(7) != null && inv.getItem(7).getType() == Material.DIAMOND) {
                                                                if (inv.getItem(8) != null && inv.getItem(8).getType() == Material.LAVA_BUCKET) {
                                                                    if (inv.getItem(9) != null && inv.getItem(9).getType() == Material.DIAMOND) {
                                                                        new BukkitRunnable() {

                                                                            @Override
                                                                            public void run() {
                                                                                inv.setResult(Items.getKnockBackStick());
                                                                            }
                                                                        }.runTaskLater(Main.getPlugin(), 2L);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
