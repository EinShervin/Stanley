package ch.lepinat.shervin.helper;

import ch.lepinat.shervin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class FlySoupManager {

    public static final HashMap<String, Info> flyingPlayers = new HashMap<>();

    public static void flugTimer(Player p, long timer) {
        int taskId;
        String uuid = p.getUniqueId().toString();

        Long time;
        if ((time = handleTime(uuid)) != null) {
            timer += time;
        }

        p.setAllowFlight(true);
        p.setFlying(true);

        long finalTimer = timer;
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            long countdown = finalTimer;

            @Override
            public void run() {
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

                        flyingPlayers.remove(uuid);

                    }
                } else {
                    countdown--;
                }
            }
        }, 0, 20);

        handlePlayerList(uuid, taskId, finalTimer);
    }

    private static Long handleTime(String uuid) {
        Info info;

        if ((info = flyingPlayers.get(uuid)) != null) {
            return info.getTime();
        }

        return null;
    }

    private static void handlePlayerList(String uuid, int taskId, long timer) {
        Info info;

        if ((info = flyingPlayers.get(uuid)) != null) {
            flyingPlayers.remove(uuid);
            flyingPlayers.put(uuid, new Info(taskId, timer + info.getTime()));
        } else {
            flyingPlayers.put(uuid, new Info(taskId, timer));
        }
    }
}
