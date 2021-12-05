package ch.lepinat.shervin.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ccCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] zusatz) {

        if (sender instanceof Player p) {
            if (p.hasPermission("Stanley.cc")) {
                if (zusatz.length == 0) {
                    for (int i = 0; i <= 100; i++) {
                        Bukkit.broadcast(Component.text(" "));
                    }
                    Bukkit.broadcast(Component.text("§aDer Chat wurde von§b " + p.getName() + "§a geleert§7!"));
                } else {
                    p.sendMessage("§cBitte benutze §a/cc§7!");
                }
            } else {
                p.sendMessage("§cDazu hast du keine Rechte§7!");
            }
        }

        return false;
    }

}
