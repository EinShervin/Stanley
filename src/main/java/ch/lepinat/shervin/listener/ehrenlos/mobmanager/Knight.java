package ch.lepinat.shervin.listener.ehrenlos.mobmanager;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

import static ch.lepinat.shervin.listener.ehrenlos.EhrenlosListener.randomarmor;
import static ch.lepinat.shervin.listener.ehrenlos.EhrenlosListener.randomweapon;

public class Knight extends Mob {
    private final String name = "§3Knight";
    private final EntityType entityType = EntityType.ZOMBIE;
    private final HashMap<Difficulty, Material> weapons = new HashMap<>() {{
        put(Difficulty.EASY, Material.IRON_AXE);
        put(Difficulty.EASY, Material.WOODEN_SWORD);
        put(Difficulty.MEDIUM, Material.IRON_SWORD);
        put(Difficulty.HARD, Material.DIAMOND_SWORD);
    }};
    private final HashMap<Difficulty, Material[]> armors = new HashMap<>() {{
        put(Difficulty.EASY, new Material[]{Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET});
        put(Difficulty.MEDIUM, new Material[]{Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET});
        put(Difficulty.HARD, new Material[]{Material.IRON_BOOTS, Material.IRON_LEGGINGS, Material.IRON_CHESTPLATE, Material.IRON_HELMET});
    }};

    @Override
    public String getName() {
        return name;
    }

    @Override
    public EntityType getEntityType() {
        return entityType;
    }

    @Override
    public ItemStack getWeapon(Difficulty difficulty) {
        return new ItemStack(randomweapon(weapons, difficulty), 1);
    }

    @Override
    public ItemStack[] getArmor(Difficulty difficulty) {
        ArrayList<ItemStack> itemStack = new ArrayList<>();
        for (Material material : randomarmor(armors, difficulty)) {
            itemStack.add(new ItemStack(material, 1));
        }
        return itemStack.toArray(new ItemStack[0]);
    }
}
