package ch.lepinat.shervin.listener;

import ch.lepinat.shervin.helper.Info;
import ch.lepinat.shervin.main.Config;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;
import java.time.Instant;

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

        Info info;
        String uuid = p.getUniqueId().toString();
        if ((info = SoupListener.flyingPlayers.get(uuid)) != null) {
            SoupListener.flyingPlayers.remove(uuid);
            try {
                Config.setTimer(uuid, info.getTimer() / 1000);
                System.out.println(info.getTimer());
                System.out.println(info.getTimer() / 1000);
                System.out.println(Instant.now().toEpochMilli());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
