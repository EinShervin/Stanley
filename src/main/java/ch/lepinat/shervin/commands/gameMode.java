package ch.lepinat.shervin.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class gameMode implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("stanley.creative")) {
                if (args.length == 1) {
                    int number;
                    try {
                        number = Integer.parseInt(args[0]);
                    } catch (NumberFormatException ex) {
                        p.sendMessage("§cVallah kein Zahl§7, §ckannst du nichtmal schreiben ODER WAS§7.");
                        return false;
                    }
                    if (number == 0) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage("§aDu bist nun im §bSurvialmodus§7.");
                    } else if (number == 1) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage("§aDu bist nun im §bCreativemodus§7.");
                    } else if (number == 2) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage("§aDu bist nun im §bAdventuremoduss§7.");
                    } else if (number == 3) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage("§aDu bist nun im §bSpectatormodus§7.");
                    } else {
                        p.sendMessage("§cBitte gebe eine Zahl von §b0-3 §cein§7.");
                    }
                } else if (args.length == 2) {
                    Player t = Bukkit.getPlayer(args[1]);
                    if (t != null) {
                        int number;
                        try {
                            number = Integer.parseInt(args[0]);
                        } catch (NumberFormatException ex) {
                            p.sendMessage("§cVallah kein Zahl§7, §ckannst du nichtmal schreiben ODER WAS§7.");
                            return false;
                        }
                        if (number == 0) {
                            t.setGameMode(GameMode.SURVIVAL);
                            t.sendMessage("§aDu wurdest von §b" + p.getName() + " §ain den §bSurvialmodus §agesetzt§7.");
                            p.sendMessage("§aDu hast §b" + t.getName() + " §ain den §bSurvivalmodus §agesetzt§7.");
                        } else if (number == 1) {
                            t.setGameMode(GameMode.CREATIVE);
                            t.sendMessage("§aDu wurdest von §b" + p.getName() + " §ain den §bCreativemodus §agesetzt§7.");
                            p.sendMessage("§aDu hast §b" + t.getName() + " §ain den §bCreativmodus §agesetzt§7.");
                        } else if (number == 2) {
                            t.setGameMode(GameMode.ADVENTURE);
                            t.sendMessage("§aDu wurdest von §b" + p.getName() + " §ain den §bAdventuremodus §agesetzt§7.");
                            p.sendMessage("§aDu hast §b" + t.getName() + " §ain den §bAdventuremodus §agesetzt§7.");
                        } else if (number == 3) {
                            t.setGameMode(GameMode.SPECTATOR);
                            t.sendMessage("§aDu wurdest von §b" + p.getName() + " §ain den §bSpectatormodus §agesetzt§7.");
                            p.sendMessage("§aDu hast §b" + t.getName() + " §ain den §bSpectatormodus §agesetzt§7.");
                        } else {
                            p.sendMessage("§cBitte gebe eine Zahl von §b0-3 §cein§7.");
                        }
                    } else {
                        p.sendMessage("§cDer Spieler §7§l" + args[1] + " §cist nicht auf diesem Server Online§7.");
                    }
                } else {
                    p.sendMessage("§cBitte gebe eine Zahl von §b0-3 §cein§7.");
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
