package ch.lepinat.shervin.stanley.listener.ehrenlos;

import ch.lepinat.shervin.stanley.listener.ehrenlos.mobmanager.*;
import ch.lepinat.shervin.stanley.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.*;


public class EhrenlosListener implements Listener {

    int taskID1, taskID2;
    boolean ID2 = false;
    public static HashMap<UUID, ArrayList<LivingEntity>> list = new HashMap<>();
    private static final HashMap<UUID, int[]> wellencounter = new HashMap<>();
    Mob[] mobs = {new Archer(), new Paladin(), new Peasant(), new Knight()};

    @EventHandler(priority = EventPriority.LOWEST)
    public void Ehrenlos(PlayerJoinEvent e) {
        taskID1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {
            if (/*Math.random() * 100 < 25 && */e.getPlayer().getUniqueId().toString().equals("1378f00d-6a2a-45cb-8de9-56623f10be2a")) {
                generateWave(e.getPlayer());
            }
        }, 100, 72000);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void mobgetkilled(EntityDeathEvent e) {
        if (!list.isEmpty()) {
            if (e.getEntityType().equals(EntityType.ZOMBIE) || e.getEntityType().equals(EntityType.SKELETON)) {
                LivingEntity entity = e.getEntity();
                Player p;
                if ((p = entity.getKiller()) != null) {
                    if (list.containsKey(p.getUniqueId())) {
                        list.get(p.getUniqueId()).remove(entity);
                        p.sendMessage("§aNur noch §6" + list.get(p.getUniqueId()).size() + " §aMobs§7.");
                    }
                } else {
                    list.values().forEach(arrayList -> {
                        if (arrayList.contains(entity)) {
                            arrayList.remove(entity);
                            return;
                        }
                    });
                }
            }
        }
    }

    private void generateWave(Player p) {
        Difficulty difficulty;
        int chance = (int) Math.round(Math.random() * 100);
        if (chance < 25) {
            difficulty = Difficulty.HARD;
            chance = 5;
        } else if (chance < 50) {
            difficulty = Difficulty.MEDIUM;
            chance = 7;
        } else {
            difficulty = Difficulty.EASY;
            chance = 10;
        }
        list.put(p.getUniqueId(), new ArrayList<>());
        wellencounter.put(p.getUniqueId(), new int[]{CreateMob.getRandomNumber(2, 6), 1});
        p.sendMessage("§cDie §6" + wellencounter.get(p.getUniqueId())[1] + ". §cWelle mit der Schwirigkeit §6" + difficulty.name() + " §ckommt auf dich zu§7!");
        CreateMob.Create(mobs, p, difficulty, 30, chance);
        taskID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {

            ID2 = true;
            if (list.get(p.getUniqueId()).isEmpty()) {
                list.remove(p.getUniqueId());
                p.sendMessage(String.valueOf(wellencounter.get(p.getUniqueId())[1]));
                if (wellencounter.get(p.getUniqueId())[1] + 1 <= wellencounter.get(p.getUniqueId())[0]) {
                    wellencounter.put(p.getUniqueId(), new int[]{wellencounter.get(p.getUniqueId())[0], wellencounter.get(p.getUniqueId())[1] + 1});
                    p.sendMessage(String.valueOf(wellencounter.get(p.getUniqueId())[1]));
                    p.sendMessage("§aGut gemacht");
                    generateWave(p);
                } else {
                    wellencounter.remove(p.getUniqueId());
                    p.sendMessage("§aNice geschafft boii§7.");
                }
                ID2 = false;
                Bukkit.getScheduler().cancelTask(taskID2);
            }
        }, 200, 40);
    }

    public static Material[] randomarmor(HashMap<Difficulty, Material[]> map, Difficulty difficulty) {
        HashMap<Difficulty, Material[]> list = new HashMap<>();
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getKey() == difficulty) {
                list.put((Difficulty) entry.getKey(), (Material[]) entry.getValue());
            }
        }
        List value = new ArrayList(list.values());
        return (Material[]) value.get((int) (Math.random() * (value.size() - 1)));
    }

    public static Material randomweapon(HashMap<Difficulty, Material> map, Difficulty difficulty) {
        HashMap<Difficulty, Material> list = new HashMap<>();
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getKey() == difficulty) {
                list.put((Difficulty) entry.getKey(), (Material) entry.getValue());
            }
        }
        List value = new ArrayList(list.values());
        return (Material) value.get((int) (Math.random() * (value.size() - 1)));
    }
}
