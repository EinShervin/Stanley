package ch.lepinat.shervin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Heal implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("Stanley.heal")) {
                if (args.length == 0) {
                    p.setHealth(20);
                    p.setFoodLevel(20);
                    p.setRemainingAir(300);
                    p.sendMessage("§aDu wurdest geheilt§7.");
                } else if (args.length == 1) {
                    Player t;
                    if ((t = Bukkit.getPlayer(args[0])) != null) {
                        t.setHealth(20);
                        t.setFoodLevel(20);
                        t.setRemainingAir(300);
                        t.sendMessage("§aDu wurdest von §b" + p.getName() + " §ageheilt§7.");
                        p.sendMessage("§aDu hast §b" + t.getName() + " §ageheilt§7.");
                    } else {
                        p.sendMessage("§cDer Spieler §7§l" + args[0] + " §cist nicht auf diesem Server Online§7.");
                    }
                } else {
                    p.sendMessage("§cBitte benutze §a/heal §7<§c§lSPIELER§7>!");
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
