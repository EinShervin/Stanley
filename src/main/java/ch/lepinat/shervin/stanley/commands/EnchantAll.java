package ch.lepinat.shervin.stanley.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class EnchantAll implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.getUniqueId().toString().equals("1378f00d-6a2a-45cb-8de9-56623f10be2a")) {
                if (!p.getInventory().getItemInMainHand().getType().isAir()) {
                    ItemStack item = p.getInventory().getItemInMainHand().clone();
                    Map<Enchantment, Integer> map = new HashMap<>();
                    for (Enchantment enchantment : Enchantment.values()) {
                        if (enchantment.canEnchantItem(item)) {
                            map.put(enchantment, enchantment.getMaxLevel());
                        }
                    }
                    p.getInventory().getItemInMainHand().addEnchantments(map);
                    p.sendMessage("§aDein Item wurde enchantet§7.");
                } else {
                    p.sendMessage("§cVallah das Luft§7.");
                }
            } else {
                p.sendMessage("§bHihi§7, §6du bist nicht Shervin §clol§7.");
            }
        } else {
            sender.sendMessage("§cDu musst ein §bSpieler §csein um diesen Command zu benutzen§7.");
        }
        return false;
    }
}
