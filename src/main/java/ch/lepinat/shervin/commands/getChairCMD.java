package ch.lepinat.shervin.commands;

import ch.lepinat.shervin.crafting.ChairRecipe;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getChairCMD implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player p) {
            if (p.getUniqueId().toString().equals("1378f00d-6a2a-45cb-8de9-56623f10be2a")) {
                p.getInventory().addItem(ChairRecipe.getChair());
            } else {
                p.sendMessage("§bHihi§7, §6du bist nicht Shervin §clol§7.");
            }
        } else {
            sender.sendMessage("§cDu musst ein §bSpieler §csein um diesen Command zu benutzen§7.");
        }
        return false;
    }
}
