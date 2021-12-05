package ch.lepinat.shervin.listener;

import ch.lepinat.shervin.helper.Items;
import ch.lepinat.shervin.main.Config;
import ch.lepinat.shervin.main.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SittingListener implements Listener {

    private int taskID;
    private static final HashMap<Location, UUID> chairs = new HashMap<>();
    private static final ArrayList<Location> lock = new ArrayList<>();

    @EventHandler
    public void spawnegg(PlayerInteractEvent e) {
        if (e.getItem() != null && e.getItem().isSimilar(Items.getChair())) {
            if (e.getClickedBlock() != null && e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (Items.getChairMaterials().containsValue(e.getClickedBlock().getType())) {
                    try {
                        Config.setChairLocation(e.getClickedBlock().getLocation(), e.getPlayer().getUniqueId());
                    } catch (IOException ioException) {
                        e.getPlayer().sendMessage("§cFehler");
                        return;
                    }
                    e.getPlayer().getInventory().remove(new ItemStack(Material.PUFFERFISH_SPAWN_EGG, 1));
                    e.setCancelled(true);
                    e.getPlayer().sendMessage("§aHabe gemacht funktionier§7.");
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void sit(PlayerInteractEvent e) {
        if (e.getClickedBlock() != null && e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Block block = e.getClickedBlock();
            if (!e.getPlayer().isSneaking()) {
                if (Items.getChairMaterials().containsValue(block.getType())) {
                    if (Config.getChairLocation(block.getLocation())) {
                        Player p = e.getPlayer();
                        if (!lock.contains(block.getLocation())) {
                            if (!chairs.containsKey(block.getLocation())) {
                                chairs.put(block.getLocation(), p.getUniqueId());
                                Location location = block.getLocation().subtract(-0.5, 1.2, -0.5);
                                World world = location.getWorld();
                                if (world != null) {
                                    ArmorStand chair = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
                                    chair.setGravity(false);
                                    chair.setVisible(false);
                                    chair.setInvulnerable(false);
                                    chair.addPassenger(p);
                                }
                            } else if (p.getUniqueId() != chairs.get(block.getLocation())) {
                                p.sendActionBar(Component.text(
                                    "§7" + Bukkit.getPlayer(chairs.get(block.getLocation())).name() + " §csitzt bereits auf diesem Stuhl§7."));
                            }
                        } else {
                            p.sendActionBar(Component.text("§cDu kannst hier gerade nicht sitzen§7, §cwarte einen Moment§7."));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDismount(EntityDismountEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getDismounted() instanceof ArmorStand) {
                scheduler(e.getEntity().getLocation().getBlock().getLocation().subtract(0, -1, 0));
                chairs.remove(e.getEntity().getLocation().getBlock().getLocation().subtract(0, -1, 0));
                e.getDismounted().remove();
            }
        }
    }

    @EventHandler
    public void breakblock(BlockBreakEvent e) {
        if (Items.getChairMaterials().containsValue(e.getBlock().getType())) {
            if (Config.getChairLocation(e.getBlock().getLocation())) {
                if (!chairs.isEmpty()) {
                    if (chairs.containsKey(e.getBlock().getLocation()) && Bukkit.getPlayer(chairs.get(e.getBlock().getLocation())).getLocation().getBlock()
                        .getLocation().subtract(0, -1, 0).equals(e.getBlock().getLocation())) {
                        e.setCancelled(true);
                    } else {
                        try {
                            Config.removeChairLocation(e.getBlock().getLocation());
                        } catch (IOException ioException) {
                            e.getPlayer().sendMessage("§cFEHLER");
                        }
                        chairs.remove(e.getBlock().getLocation());
                        e.getPlayer().getInventory().addItem(Items.getChair());
                    }
                } else {
                    try {
                        Config.removeChairLocation(e.getBlock().getLocation());
                    } catch (IOException ioException) {
                        e.getPlayer().sendMessage("§cFEHLER");
                    }
                    e.getPlayer().getInventory().addItem(Items.getChair());
                }
            }
        }
    }

    private void scheduler(Location location) {
        lock.add(location);
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            float countdown = 3;

            @Override
            public void run() {
                if (countdown <= 0) {
                    lock.remove(location);
                    Bukkit.getScheduler().cancelTask(taskID);
                }
                countdown--;
            }
        }, 0, 20);
    }
}
