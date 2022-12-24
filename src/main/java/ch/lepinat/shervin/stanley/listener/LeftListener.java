package ch.lepinat.shervin.stanley.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeftListener implements Listener {

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        String name = p.getName();
        e.quitMessage(Component.text("§c- §7" + name));
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (!player.getName().equalsIgnoreCase(name)) {
                player.sendActionBar(Component.text("§7" + name + " §cist vom Server gegangen§7."));
            }
        }
    }

}
