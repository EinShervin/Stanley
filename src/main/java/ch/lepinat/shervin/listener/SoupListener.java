package ch.lepinat.shervin.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ch.lepinat.shervin.exceptions.LeftException;
import ch.lepinat.shervin.exceptions.isNullException;
import ch.lepinat.shervin.main.Config;
import ch.lepinat.shervin.main.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SoupListener implements Listener {

    private int taskID1;
    private int taskID2;

    public static ArrayList<UUID> flying = new ArrayList<>();

    public void flugTimer(Player p, long timer) {

        if (!flying.contains(p.getUniqueId())) flying.add(p.getUniqueId());

        long starttime;
        try {
            starttime = Config.setTimer(p.getUniqueId(), timer);
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
                    flying.remove(p.getUniqueId());

                    p.sendMessage("§cDu kannst nicht mehr fliegen§7.");
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 25, 1));
                    countdown = -1;
                } else if (countdown == 60) {
                    p.sendMessage("§cDu kannst nur noch §660 Sekunden §cfliegen§7.");
                } else if (countdown == 30) {
                    p.sendMessage("§cDu kannst nur noch §630 Sekunden §cfliegen§7.");
                } else if (countdown == 3) {
                    p.sendMessage("§cDu kannst nur noch §63 Sekunden §cfliegen§7.");
                } else if (countdown == 2) {
                    p.sendMessage("§cDu kannst nur noch §62 Sekunden §cfliegen§7.");
                } else if (countdown == 1) {
                    p.sendMessage("§cDu kannst nur noch §61 Sekunden §cfliegen§7.");
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
                            stop();
                        }
                    }
                } else {
                    countdown--;
                }
            }
        }, 0, 20);

        taskID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {
            if ((Config.getstarttime(p.getUniqueId())) != starttime) {
                stop();
            }
        }, 40, 50);
    }

    private void stop() {
        Bukkit.getScheduler().cancelTask(taskID2);
        Bukkit.getScheduler().cancelTask(taskID1);
    }

    @EventHandler
    public void onSoupdrinking(PlayerItemConsumeEvent e) {
        if (e.getItem().isSimilar(getFlySoup())) {
            Player p = e.getPlayer();
            if (flying.contains(p.getUniqueId())) {
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

    public static ItemStack getFlySoup() {
        ItemStack flySoup = new ItemStack(Material.POTION, 1);
        ItemMeta meta = flySoup.getItemMeta();
        meta.displayName(Component.text("§6Flyingsoup"));
        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("§fFlieg du Jude"));
        meta.lore(lore);
        flySoup.setItemMeta(meta);
        return flySoup;
    }
}
