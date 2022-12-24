package ch.lepinat.shervin.stanley.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;

public class Burn implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (!p.getInventory().isEmpty()) {
                PlayerInventory inventory = p.getInventory();
                HashMap<Material, Material> concreteTypes = new HashMap<>() {{
                    put(Material.BLACK_CONCRETE_POWDER, Material.BLACK_CONCRETE);
                    put(Material.BLUE_CONCRETE_POWDER, Material.BLUE_CONCRETE);
                    put(Material.BROWN_CONCRETE_POWDER, Material.BROWN_CONCRETE);
                    put(Material.CYAN_CONCRETE_POWDER, Material.CYAN_CONCRETE);
                    put(Material.GRAY_CONCRETE_POWDER, Material.GRAY_CONCRETE);
                    put(Material.GREEN_CONCRETE_POWDER, Material.GREEN_CONCRETE);
                    put(Material.LIME_CONCRETE_POWDER, Material.LIME_CONCRETE);
                    put(Material.MAGENTA_CONCRETE_POWDER, Material.MAGENTA_CONCRETE);
                    put(Material.ORANGE_CONCRETE_POWDER, Material.ORANGE_CONCRETE);
                    put(Material.PINK_CONCRETE_POWDER, Material.PINK_CONCRETE);
                    put(Material.PURPLE_CONCRETE_POWDER, Material.PURPLE_CONCRETE);
                    put(Material.RED_CONCRETE_POWDER, Material.RED_CONCRETE);
                    put(Material.WHITE_CONCRETE_POWDER, Material.WHITE_CONCRETE);
                    put(Material.YELLOW_CONCRETE_POWDER, Material.YELLOW_CONCRETE);
                    put(Material.LIGHT_BLUE_CONCRETE_POWDER, Material.LIGHT_BLUE_CONCRETE);
                    put(Material.LIGHT_GRAY_CONCRETE_POWDER, Material.LIGHT_GRAY_CONCRETE);
                }};

                for (ItemStack itemStack : Arrays.stream(p.getInventory().getContents())
                    .filter(itemStack -> itemStack != null && concreteTypes.containsKey(itemStack.getType())).toList()) {
                    inventory.remove(itemStack);
                    itemStack.setType(concreteTypes.get(itemStack.getType()));
                    inventory.addItem(itemStack);
                }
            } else {
                p.sendMessage("§cdu hs");
            }
        } else {
            sender.sendMessage("§cDu musst ein §bSpieler §csein um diesen Command zu benutzen§7.");
        }
        return false;
    }
}
