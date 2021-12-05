package ch.lepinat.shervin.commands;

import ch.lepinat.shervin.crafting.Head;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

public class HeadCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player p) {
            if (p.getInventory().getItemInMainHand().isSimilar(Head.createHead())) {
                if (args.length == 1) {
                    p.getInventory().remove(Head.createHead());
                    p.getInventory().addItem(createSkull(args[0]));
                    p.sendMessage("§aWHHOOAUUHH");
                } else {
                    p.sendMessage("§cGebe einen Namen ein§7.");
                }
            } else {
                p.sendMessage("§cVallah das keine Magische Kopf§7.");
            }
        } else {
            sender.sendMessage("§cDu musst ein §bSpieler §csein um diesen Command zu benutzen§7.");
        }
        return false;
    }

    private ItemStack createSkull(@NotNull String name) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(name);
        skullMeta.setOwningPlayer(offlinePlayer);
        head.setItemMeta(skullMeta);
        return head;
    }
}
