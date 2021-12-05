package ch.lepinat.shervin.listener;

import ch.lepinat.shervin.exceptions.LeftException;
import ch.lepinat.shervin.exceptions.isNullException;
import ch.lepinat.shervin.helper.Items;
import ch.lepinat.shervin.main.Config;
import ch.lepinat.shervin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;
import java.util.HashMap;

public class SoupListener implements Listener {

    private int taskID1;

    public static HashMap<String, Integer> flyingPlayers = new HashMap<>();

    public void flugTimer(Player p, long timer) {

        long startTime;
        try {
            startTime = Config.setTimer(p.getUniqueId(), timer);
        } catch (IOException e) {
            p.sendMessage("§cFehler§7.");
            return;
        }
        p.setAllowFlight(true);
        p.setFlying(true);
        taskID1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            long countdown = timer;

            @Override
            public void run() {
                if (countdown == 0) {
                    flyingPlayers.remove(p.getUniqueId());

                    p.sendMessage("§cDu kannst nicht mehr fliegen§7.");
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 25, 1));
                    countdown = -1;
                } else if (countdown == 60 || countdown == 30 || countdown == 3 || countdown == 2 || countdown == 1) {
                    p.sendMessage("§cDu kannst nur noch §6" + countdown + " Sekunden §cfliegen§7.");
                }
                if (countdown == -1) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 25, 1));
                    if (p.isOnGround() || p.isSwimming() || p.isGliding() || p.isSleeping() || p.isInsideVehicle()) {
                        p.removePotionEffect(PotionEffectType.SLOW_FALLING);
                        try {
                            Config.removeTimer(p.getUniqueId());
                        } catch (IOException e) {
                            p.sendMessage("§cFehler§7.");
                        } finally {
                            Bukkit.getScheduler().cancelTask(taskID1);
                        }
                    }
                } else {
                    countdown--;
                }
            }
        }, 0, 20);
    }

    @EventHandler
    public void onSoupDrinking(PlayerItemConsumeEvent e) {
        if (e.getItem().isSimilar(Items.getFlySoup())) {
            Player p = e.getPlayer();
            if (flyingPlayers.containsKey(p.getUniqueId().toString())) {
                try {
                    flugTimer(p, Config.getTimer(p.getUniqueId()) / 1000 + 1800);
                } catch (LeftException | isNullException exception) {
                    exception.printStackTrace();
                }
            } else {
                flugTimer(p, 1800);
            }
        }
    }
}
