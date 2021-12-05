package ch.lepinat.shervin.commands;

import ch.lepinat.shervin.main.TimeFormatter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ch.lepinat.shervin.listener.SoupListener;

public class FlyCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arg) {
        if (sender instanceof Player p) {
            if (p.hasPermission("stanley.fly")) {
                if (arg.length == 0) {
                    if (p.getAllowFlight()) {
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        p.sendMessage("§cDu kannst nun nicht mehr fliegen§7.");
                    } else {
                        p.setAllowFlight(true);
                        p.setFlying(true);
                        p.sendMessage("§aDu kannst nun fliegen§7.");
                    }
                } else if (arg.length == 1) {
                    if (Bukkit.getPlayer(arg[0]) != null) {
                        Player t = Bukkit.getPlayer(arg[0]);
                        if (t.getAllowFlight()) {
                            t.setAllowFlight(false);
                            t.setFlying(false);
                            t.sendMessage("§aDu kannst dank §b" + p.getName() + " §anicht mehr fliegen§7.");
                            p.sendMessage("§aDu hast §b" + t.getName() + " §adie kraft des fliegens genommen§7.");
                        } else {
                            t.setAllowFlight(true);
                            t.setFlying(true);
                            t.sendMessage("§aDu kannst dank §b" + p.getName() + " §anun fliegen§7.");
                            p.sendMessage("§aDu hast §b" + t.getName() + " §afliegen beigebracht§7.");
                        }
                    } else {
                        p.sendMessage("§cDer Spieler §7§l" + arg[0] + " §cist nicht auf diesem Server Online§7.");
                    }
                } else if (arg.length == 2) {
                    Player t;
                    if ((t = Bukkit.getPlayer(arg[0])) != null) {
                        int number;
                        try {
                            number = Integer.parseInt(arg[1]);
                        } catch (NumberFormatException ex) {
                            p.sendMessage("§cKein Zahl§7.");
                            return false;
                        }
                        if (number > 0) {
                            t.getPlayer().setAllowFlight(true);
                            t.getPlayer().setFlying(true);
                            t.sendMessage("§6" + p.getName() + " §ahat dir fliegen für §6" + TimeFormatter.format(number * 1000L, 'a') + " §agegeben§7.");
                            p.sendMessage("§aDu hast §6" + t.getName() + " §afliegen gegeben für §6" + TimeFormatter.format(number * 1000L, 'a') + "§7.");
                            SoupListener soupListener = new SoupListener();
                            soupListener.flugTimer(t.getPlayer(), number);
                        } else {
                            p.sendMessage("§cgib mal was richtiges ein. lost du kek");
                        }
                    } else {
                        p.sendMessage("§cDer Spieler §7§l" + arg[0] + " §cist nicht auf diesem Server Online§7.");
                    }
                } else {
                    p.sendMessage("§cgib mal was richtiges ein. lost du kedddk");
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
