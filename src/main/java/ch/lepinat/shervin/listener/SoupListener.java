package ch.lepinat.shervin.listener;

import ch.lepinat.shervin.helper.FlySoupManager;
import ch.lepinat.shervin.helper.Items;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class SoupListener implements Listener {

    @EventHandler
    public void onSoupDrinking(PlayerItemConsumeEvent e) {
        if (e.getItem().isSimilar(Items.getFlySoup())) {
            Player p = e.getPlayer();
            FlySoupManager.flugTimer(p, 1800);
        }
    }
}
