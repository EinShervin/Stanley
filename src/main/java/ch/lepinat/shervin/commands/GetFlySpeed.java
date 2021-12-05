package ch.lepinat.shervin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetFlySpeed implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("Stanley.getspeed")) {
                if (args.length == 0) {
                    p.sendMessage("§aDu hast einen Flyspeed von §6" + Math.round(p.getFlySpeed() * 10) + "§7.");
                } else if (args.length == 1) {
                    Player t = Bukkit.getPlayer(args[0]);
                    if (t != null) {
                        p.sendMessage("§6" + t.getName() + " §ahat einen Flyspeed von §6" + Math.round(t.getFlySpeed() * 10) + "§7.");
                    } else {
                        p.sendMessage("§cDer Spieler §7§l" + args[0] + " §cist nicht auf diesem Server Online§7.");
                    }
                } else {
                    p.sendMessage("§cgib mal was richtiges ein. lost du kek");
                }
            } else {
                p.sendMessage("§cDu hast dazu keine Rechte§7.");
            }
        } else {
            sender.sendMessage("§cDu musst ein §bSpieler §csein um diesen Command zu benutzen§7.");
        }
        return false;
    }
}
