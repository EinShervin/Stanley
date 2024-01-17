package ch.lepinat.shervin.stanley.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GameMode implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cDu musst ein §bSpieler §csein um diesen Command zu benutzen§7.");
            return false;
        }
        if (!p.hasPermission("stanley.creative")) {
            p.sendMessage("§cDu hast dazu keine Rechte§7.");
            return false;
        }
        if (args.length == 0) {
            p.sendMessage("§cCommand nicht komplett!");
            return false;
        }
        Integer num = getCommandNum(args);
        if (num == null) {
            p.sendMessage("§cVallah kein Zahl§7, §ckannst du nichtmal schreiben ODER WAS§7.");
            return false;
        }
        if (num > 3 || num < 0) {
            p.sendMessage("§cBitte gebe eine Zahl von §b0-3 §cein§7.");
            return false;
        }
        if (args.length == 1) {
            p.setGameMode(getGameModeByNum(num));
            p.sendMessage("§aDu bist nun im §b" + getGameModeString(num) + "§7.");
        } else if (args.length == 2) {
            Player t = Bukkit.getPlayer(getPlayerString(args));
            if (t == null) {
                p.sendMessage("§cDer Spieler §7§l" + getPlayerString(args) + " §cist nicht auf diesem Server Online§7.");
                return false;
            }
            t.setGameMode(getGameModeByNum(num));
            t.sendMessage("§aDu wurdest von §b" + p.getName() + " §ain den §b" + getGameModeString(num) + " §agesetzt§7.");
            p.sendMessage("§aDu hast §b" + t.getName() + " §ain den §b" + getGameModeString(num) + " §agesetzt§7.");
        } else {
            p.sendMessage("§cBitte gebe eine Zahl von §b0-3 §cein§7.");
        }
        return true;
    }

    private String getPlayerString(String[] args) {
        return args[1];
    }

    private Integer getCommandNum(String[] args) {
        try {
            return Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private String getGameModeString(int num) {
        return switch (num) {
            case 0 -> "Survialmodus";
            case 1 -> "Creativemodus";
            case 2 -> "Adventuremodus";
            case 3 -> "Spectatormodus";
            default -> "";
        };
    }

    private org.bukkit.GameMode getGameModeByNum(int num) {
        return switch (num) {
            case 0 -> org.bukkit.GameMode.SURVIVAL;
            case 1 -> org.bukkit.GameMode.CREATIVE;
            case 2 -> org.bukkit.GameMode.ADVENTURE;
            case 3 -> org.bukkit.GameMode.SPECTATOR;
            default -> null;
        };
    }
}
