package ch.lepinat.shervin.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class resetnameCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arg) {
        if (sender instanceof Player p) {
            if (!p.getInventory().getItemInMainHand().getItemMeta().displayName().toString().equals("")) {
                if (!p.getInventory().getItemInMainHand().getType().isAir()) {
                    if (p.getInventory().contains(Material.EMERALD, 2) || p.getUniqueId().toString().equals("1378f00d-6a2a-45cb-8de9-56623f10be2a")) {
                        if (arg.length == 0) {
                            ItemStack Item = new ItemStack(p.getInventory().getItemInMainHand());
                            ItemMeta ItemMeta = Item.getItemMeta();
                            ItemMeta.setDisplayName("");
                            Item.setItemMeta(ItemMeta);
                            p.getInventory().getItemInMainHand().setItemMeta(ItemMeta);
                            p.sendMessage("§aDu hast den Itemnamen §berfolgreich §azurueckgesetzt§7.");
                        } else {
                            p.sendMessage("§cDu kannst nicht noch mehr einegeben als §a/resetname§7.");
                        }
                    } else {
                        p.sendMessage("§cDu hast dazu keine 2 §aEmeralds§7.");
                    }
                } else {
                    p.sendMessage("§cDu hast kein Item in der Hand§7.");
                }
            } else {
                p.sendMessage("§cDas Item hat schon seinen Original namen§7.");
            }
        } else {
            sender.sendMessage("§cDu musst ein §bSpieler §csein um diesen Command zu benutzen§7.");
        }
        return false;
    }

}
