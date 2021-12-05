package ch.lepinat.shervin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getflyspeedCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arg) {
        if (sender instanceof Player p) {
            if (p.hasPermission("Stanley.getspeed")) {
                if (arg.length == 0) {
                    p.sendMessage("§aDu hast einen Flyspeed von §6" + Math.round(p.getFlySpeed() * 10) + "§7.");
                } else if (arg.length == 1) {
                    Player t = Bukkit.getPlayer(arg[0]);
                    if (t != null) {
                        p.sendMessage("§6" + t.getName() + " §ahat einen Flyspeed von §6" + Math.round(t.getFlySpeed() * 10) + "§7.");
                    } else {
                        p.sendMessage("§cDer Spieler §7§l" + arg[0] + " §cist nicht auf diesem Server Online§7.");
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
