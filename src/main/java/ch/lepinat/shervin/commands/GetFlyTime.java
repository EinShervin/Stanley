package ch.lepinat.shervin.commands;

import ch.lepinat.shervin.exceptions.LeftException;
import ch.lepinat.shervin.exceptions.isNullException;
import ch.lepinat.shervin.listener.SoupListener;
import ch.lepinat.shervin.main.Config;
import ch.lepinat.shervin.main.TimeFormatter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetFlyTime implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 0) {
                if (SoupListener.flyingPlayers.containsKey(p.getUniqueId().toString())) {
                    try {
                        p.sendMessage("§aDu kannst noch " + TimeFormatter.format(Config.getTimer(p.getUniqueId()), 'a') + " §afliegen§7.");
                    } catch (LeftException | isNullException e) {
                        p.sendMessage("§cFehler");
                    }
                } else {
                    p.sendMessage("§cDu bist gerade nicht am fliegen§7.");
                }
            } else if (args.length == 1) {
                Player t;
                if ((t = Bukkit.getPlayer(args[0])) != null) {
                    try {
                        if (SoupListener.flyingPlayers.containsKey(t.getUniqueId().toString())) {
                            p.sendMessage("§aDer Spieler §6" + t.getName() + " §akann noch §6" + TimeFormatter.format(Config.getTimer(t.getUniqueId()), 'a') + " §afliegen§7.");
                        } else {
                            p.sendMessage("§cDer Spieler §l" + t.getName() + " §cist gerade nicht am fliegen§7.");
                        }
                    } catch (LeftException | isNullException e) {
                        p.sendMessage("§cFehler");
                    }
                } else {
                    p.sendMessage("§cDer Spieler §l" + args[0] + " §cist nicht Online§7.");
                }
            } else {
                p.sendMessage("§cBitte benutze §l/flytime§7.");
            }
        } else {
            sender.sendMessage("§cDu musst ein §bSpieler §csein um diesen Command zu benutzen§7.");
        }
        return false;
    }
}
