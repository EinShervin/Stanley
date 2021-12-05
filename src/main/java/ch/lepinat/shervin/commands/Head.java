package ch.lepinat.shervin.commands;

import ch.lepinat.shervin.helper.Items;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Head implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.getInventory().getItemInMainHand().isSimilar(Items.createHead())) {
                if (args.length == 1) {
                    p.getInventory().remove(Items.createHead());
                    p.getInventory().addItem(Items.createSkull(args[0]));
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
}
