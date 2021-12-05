package ch.lepinat.shervin.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DisplaynameCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p) {
            if (!p.getInventory().getItemInMainHand().getType().isAir()) {
                if (p.getInventory().contains(Material.EMERALD, 5) || p.getUniqueId().toString().equals("1378f00d-6a2a-45cb-8de9-56623f10be2a")) {
                    ItemStack Item = new ItemStack(p.getInventory().getItemInMainHand());
                    ItemMeta ItemMeta = Item.getItemMeta();
                    if (args.length == 0) {
                        p.sendMessage("§cGebe dem Item einen Namen§7!");
                    } else if (args[0].equals("random")) {
                        String[] names = {"§bMica das §cARSCHLOCH", "§aShervin der §b§lKing"};
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 5));
                        ItemMeta.displayName(Component.text(names[(int) Math.round(Math.random() * names.length)]));
                        p.getInventory().getItemInMainHand().setItemMeta(ItemMeta);
                        p.sendMessage("§aDu hast deinem Item einen §4R§6a§en§ad§9o§dm §aNamen gegeben§7.");
                    } else {
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 5));
                        String Nachricht = convertObjectArrayToString(args, " ");
                        ItemMeta.displayName(Component.text(ChatColor.translateAlternateColorCodes('&', Nachricht)));
                        p.getInventory().getItemInMainHand().setItemMeta(ItemMeta);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "§aDu hast dein Item zu §r" + Nachricht + " §abenannt§7."));
                    }
                } else {
                    p.sendMessage("§cDu hast dazu keine 5 §aEmeralds§7.");
                }
            } else {
                p.sendMessage("§cDu hast kein Item in der Hand§7.");
            }
        } else {
            sender.sendMessage("§cDu musst ein §bSpieler §csein um diesen Command zu benutzen§7.");
        }
        return false;
    }

    private static String convertObjectArrayToString(Object[] arr, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : arr)
            sb.append(obj.toString()).append(delimiter);
        return sb.substring(0, sb.length() - 1);

    }
}
