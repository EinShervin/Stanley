package ch.lepinat.shervin.stanley.listener;

import ch.lepinat.shervin.stanley.exceptions.LeftException;
import ch.lepinat.shervin.stanley.exceptions.isNullException;
import ch.lepinat.shervin.stanley.main.Config;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.*;
import java.time.Instant;
import java.util.Date;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String name = p.getName();
        e.joinMessage(Component.text("§a+ §7" + name));
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (!player.getName().equalsIgnoreCase(name)) {
                player.sendActionBar(Component.text("§7" + name + " §aist nun auf dem Server§7."));
            }
        }
        e.getPlayer().sendMessage("§6Moin§7, §6benutze §l/help Stanley §6wenn du fragen zu den Commands hast§7.");

        flySoup(p);
    }

    private void flySoup(Player p) {
        long timer;
        try {
            timer = Config.getTimer(p.getUniqueId());
        } catch (isNullException exception) {
            return;
        } catch (LeftException exception) {
            SoupListener soupListener = new SoupListener();
            soupListener.flugTimer(p, 0);
            return;
        }
        if (timer > 0) {
            SoupListener soupListener = new SoupListener();
            try {
                soupListener.flugTimer(p, (Config.getTimer(p.getUniqueId()) + new Date(Instant.now().getEpochSecond()).getTime()) / 1000);
            } catch (LeftException | isNullException leftException) {
                leftException.printStackTrace();
            }
        } else {
            try {
                Config.removeTimer(p.getUniqueId());
            } catch (IOException ioException) {
                p.sendMessage("§cdu huansohn");
            }
        }
    }
}
