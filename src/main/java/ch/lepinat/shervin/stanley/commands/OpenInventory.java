package ch.lepinat.shervin.stanley.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class OpenInventory implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.isOp()) {
                if (args.length == 1) {
                    Player t;
                    if ((t = Bukkit.getPlayer(args[0])) != null) {
                        Inventory inv = Bukkit.createInventory(t, InventoryType.PLAYER, Component.text(t.getName()));
                        inv.setContents(t.getInventory().getContents());
                        p.openInventory(inv);
                    } else {
                        p.sendMessage("§cDer Spieler ist nicht Online§7.");
                    }
                } else {
                    p.sendMessage("§cDas ist kein richtiger Name§7.");
                }
            } else {
                p.sendMessage("§cDu bist ein Unwürdiger§7.");
            }
        } else {
            sender.sendMessage("§cDu musst ein §bSpieler §csein um diesen Command zu benutzen§7.");
        }
        return false;
    }
}
