package ch.lepinat.shervin.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.Instant;

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
        System.out.println(SoupListener.flyingPlayers.get(p.getUniqueId().toString()).getTimer());
        System.out.println(Instant.now().toEpochMilli());
    }
}
