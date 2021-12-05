package ch.lepinat.shervin.commands;

import ch.lepinat.shervin.main.TimeFormatter;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class StatsCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player p) {
            String pattern = "###,###,###";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            p.sendMessage("§7----------- §6Stats §7-----------");
            p.sendMessage("§7Gelaufene Blöcke: §6" + decimalFormat.format(counter(p, new Statistic[]{Statistic.WALK_ONE_CM, Statistic.WALK_ON_WATER_ONE_CM, Statistic.WALK_UNDER_WATER_ONE_CM, Statistic.SPRINT_ONE_CM, Statistic.SWIM_ONE_CM, Statistic.CROUCH_ONE_CM}) / 100));
            p.sendMessage("§7Jumps: §6" + decimalFormat.format(p.getStatistic(Statistic.JUMP)));
            p.sendMessage("§7Damagetaken: §6" + decimalFormat.format(p.getStatistic(Statistic.DAMAGE_TAKEN)));
            p.sendMessage("§7Damagedealt: §6" + decimalFormat.format(p.getStatistic(Statistic.DAMAGE_DEALT)));
            p.sendMessage("§7Zerstörte Blöcke: §6" + decimalFormat.format(counter(p, Statistic.MINE_BLOCK, false)));
            p.sendMessage("§7Gesetze Blocke: §6" + decimalFormat.format(counter(p, Statistic.USE_ITEM, true)));
            p.sendMessage("§7Zerstörte Werkzeuge: §6" + decimalFormat.format(counter(p, Statistic.BREAK_ITEM, false)));
            p.sendMessage("§7Gespielte Zeit: §6" + TimeFormatter.format(p.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20L * 1000L, '7'));
            p.sendMessage("§7----------------------------");
        } else {
            sender.sendMessage("§cDu musst ein §bSpieler §csein um diesen Command zu benutzen§7.");
        }
        return false;
    }

    private double counter(Player p, Statistic statistic, boolean filter) {
        double counter = 0;
        if (filter) {
            for (Material material : Material.values()) {
                if (material.isBlock()) {
                    counter += p.getStatistic(statistic, material);
                }
            }
        } else {
            for (Material material : Material.values()) {
                counter += p.getStatistic(statistic, material);
            }
        }
        return counter;
    }

    private double counter(Player p, Statistic[] statistics) {
        double counter = 0;
        for (Statistic stat : statistics) {
            counter += p.getStatistic(stat);
        }
        return counter;
    }

}
