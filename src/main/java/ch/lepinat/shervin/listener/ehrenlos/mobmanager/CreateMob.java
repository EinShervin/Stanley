package ch.lepinat.shervin.listener.ehrenlos.mobmanager;

import ch.lepinat.shervin.listener.ehrenlos.EhrenlosListener;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class CreateMob {

    public static void Create(Mob[] mobs, Player p, Difficulty difficulty, int radius, int count) {
        Location location = p.getLocation();
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 200, false, false, false));
        for (Mob mob : mobs) {
            switch (mob.getEntityType()) {
                case SKELETON:
                    for (int degree = 0; degree < 360; degree += (360 / count)) {
                        double radians = Math.toRadians(degree);
                        int number = getRandomNumber(radius - radius / 3, radius);
                        double x = Math.cos(radians) * number, z = Math.sin(radians) * number;
                        Skeleton creature = (Skeleton) location.getWorld().spawnEntity(getLocation(location.clone().add(x, 0, z)), mob.getEntityType());
                        creature.setCustomName(mob.getName());
                        creature.setCustomNameVisible(true);
                        creature.getEquipment().setItemInMainHand(mob.getWeapon(difficulty));
                        creature.getEquipment().setArmorContents(mob.getArmor(difficulty));
                        creature.setTarget(p);
                        creature.setAI(true);
                        EhrenlosListener.list.get(p.getUniqueId()).add(creature);
                    }
                    break;
                case ZOMBIE:
                    for (int degree = 0; degree < 360; degree += (360 / count)) {
                        double radians = Math.toRadians(degree);
                        int number = getRandomNumber(radius - radius / 3, radius);
                        double x = Math.cos(radians) * number, z = Math.sin(radians) * number;
                        Zombie creature = (Zombie) location.getWorld().spawnEntity(getLocation(location.clone().add(x, 0, z)), mob.getEntityType());
                        creature.setCustomName(mob.getName());
                        creature.setCustomNameVisible(true);
                        creature.getEquipment().setItemInMainHand(mob.getWeapon(difficulty));
                        creature.getEquipment().setArmorContents(mob.getArmor(difficulty));
                        creature.setAdult();
                        if (mob.getName().equals("§3Paladin")) {
                            creature.getEquipment().setItemInOffHand(new ItemStack(Material.SHIELD, 1));
                        }
                        creature.setTarget(p);
                        creature.setAI(true);
                        EhrenlosListener.list.get(p.getUniqueId()).add(creature);
                    }
                    break;
            }
        }
    }

    private static Location getLocation(Location location) {
        if (location.getBlock().getType() != Material.AIR) {
            while (location.getBlock().getType() != Material.AIR) {
                location.add(0, 1, 0);
            }
        } else if (location.getBlock().getType() == Material.AIR && location.clone().subtract(0, 1, 0).getBlock().getType() == Material.AIR) {
            while (location.getBlock().getType() == Material.AIR) {
                location.subtract(0, 1, 0);
            }
            if (location.getBlock().getType() != Material.GRASS) {
                location.add(0, 1, 0);
            }
        }
        return location;
    }

    public static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }
}
