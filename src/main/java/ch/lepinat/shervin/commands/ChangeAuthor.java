package ch.lepinat.shervin.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class ChangeAuthor implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (!p.getInventory().getItemInMainHand().getType().isAir()) {
                if (p.getInventory().getItemInMainHand().getType() == Material.WRITTEN_BOOK) {
                    if (p.getUniqueId().toString().equals("1378f00d-6a2a-45cb-8de9-56623f10be2a")) {
                        if (args.length > 0) {
                            ItemStack item = new ItemStack(p.getInventory().getItemInMainHand());
                            ItemMeta itemMeta = item.getItemMeta();
                            BookMeta bookMeta = (BookMeta) itemMeta;
                            if (bookMeta != null) {
                                if (args.length == 1) {
                                    bookMeta.setAuthor(args[0]);
                                } else {
                                    bookMeta.setAuthor(convertObjectArrayToString(args, " "));
                                }
                                p.getInventory().getItemInMainHand().setItemMeta(bookMeta);
                            } else {
                             p.sendMessage("§cKaputt§7.");
                            }
                        } else {
                            p.sendMessage("§cGebe dem Autoren einen Namen§7.");
                        }
                    } else {
                        p.sendMessage("§cDu bist ein Unwürdiger§7.");
                    }
                } else {
                    p.sendMessage("§cDu hast kein geschriebenes Buch in der Hand§7.");
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
