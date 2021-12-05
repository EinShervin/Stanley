package ch.lepinat.shervin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyspeedCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arg) {

        if (sender instanceof Player p) {
            if (p.hasPermission("Stanley.Speed")) {
                if (p.getAllowFlight()) {
                    float number;
                    try {
                        number = Float.parseFloat(arg[0]);
                    } catch (NumberFormatException ex) {
                        p.sendMessage("§cBitte gebe eine Zahl ein§7!");
                        return false;
                    }
                    number = number / 10;
                    if (number > 1) {
                        p.sendMessage("§cBitte gebe eine Geschwindigkeit kleiner oder gleich 10 ein.");
                    } else if (number < 0) {
                        p.sendMessage("§cBitte gebe eine Geschwindigkeit gr§sser oder gleich 0 ein.");
                    } else if (arg.length == 1) {
                        p.setFlySpeed(number);
                        p.sendMessage("§aDein Flyspeed betr§gt nun §6" + Math.round(number * 10) + "§7.");
                    } else if (arg.length == 2) {
                        Player t = Bukkit.getPlayer(arg[1]);
                        if (t != null) {
                            t.setFlySpeed(number);
                            t.sendMessage("§aDu hast von §6" + p.getName() + " §aeinen Flyspeed von §6" + Math.round(number * 10) + " §abekommen§7.");
                            p.sendMessage("§aDu hast §6" + t.getName() + " §aeinen Flypseed von §6" + Math.round(number * 10) + " §abekommen§7.");
                        } else {
                            p.sendMessage("§cDer Spieler §7§l" + arg[0] + " §cist nicht auf diesem Server Online§7.");
                        }
                    } else {
                        p.sendMessage("§cgib mal was richtiges ein. lost du kek");
                    }
                } else {
                    p.sendMessage("§cDu darfst garnet fleigen kid§7.");
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
