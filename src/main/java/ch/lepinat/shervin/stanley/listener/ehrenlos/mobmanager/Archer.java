package ch.lepinat.shervin.stanley.listener.ehrenlos.mobmanager;

import ch.lepinat.shervin.stanley.listener.ehrenlos.EhrenlosListener;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class Archer extends Mob {
    private final String name = "§3Archer";
    private final EntityType entityType = EntityType.SKELETON;
    private final HashMap<Difficulty, Material> weapons = new HashMap<>() {{
        put(Difficulty.EASY, Material.BOW);
        put(Difficulty.MEDIUM, Material.BOW);
        put(Difficulty.HARD, Material.BOW);
    }};
    private final HashMap<Difficulty, Material[]> armors = new HashMap<>() {{
        put(Difficulty.EASY, new Material[]{Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET});
        put(Difficulty.MEDIUM, new Material[]{Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET});
        put(Difficulty.HARD, new Material[]{Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET});
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
        return new ItemStack(EhrenlosListener.randomweapon(weapons, difficulty), 1);
    }

    @Override
    public ItemStack[] getArmor(Difficulty difficulty) {
        ArrayList<ItemStack> itemStack = new ArrayList<>();
        for (Material material : EhrenlosListener.randomarmor(armors, difficulty)) {
            itemStack.add(new ItemStack(material, 1));
        }
        return itemStack.toArray(new ItemStack[0]);
    }
}
