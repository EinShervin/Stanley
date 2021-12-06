package ch.lepinat.shervin.listener;

import ch.lepinat.shervin.helper.Info;
import ch.lepinat.shervin.helper.Items;
import ch.lepinat.shervin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class SoupListener implements Listener {

    private int taskId;
    public static final HashMap<String, Info> flyingPlayers = new HashMap<>();

    public void flugTimer(Player p, long timer) {

        p.setAllowFlight(true);
        p.setFlying(true);

        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            long countdown;
            long finalTimer = timer;

            private void handlePlayerList(String uuid, int taskId) {
                Info info;
                if ((info = flyingPlayers.get(uuid)) != null) {
                    Bukkit.getScheduler().cancelTask(flyingPlayers.get(uuid).getTaskId());
                    finalTimer += info.getTimer() / 1000;
                    flyingPlayers.remove(uuid);
                }
                flyingPlayers.put(uuid, new Info(taskId, timer));
                countdown = finalTimer;
            }

            @Override
            public void run() {
                if (countdown == finalTimer) {
                    handlePlayerList(p.getUniqueId().toString(), taskId);
                }
                if (countdown == 0) {
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

                        flyingPlayers.remove(p.getUniqueId().toString());
                        Bukkit.getScheduler().cancelTask(taskId);
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
            flugTimer(p, 1800);
        }
    }
}
