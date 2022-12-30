package ch.lepinat.shervin.stanley.commands;


import ch.lepinat.shervin.stanley.helper.Items;
import com.destroystokyo.paper.event.player.PlayerRecipeBookClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class Recipes implements Listener, CommandExecutor {

    private static Inventory RecipeInventory;
    private static Inventory CraftingInventory;

    private static Inventory TestInventory;
    private static final String RecipeInvName = "§8§lAlle special Rezepte";
    private static String CraftingInvName = "Rezept für: §4";

    private static final List<Material> LightSourceList = new ArrayList<>();
    private static final List<Material> FlySoupList = new ArrayList<>();
    private static final List<Material> KnockbackStickList = new ArrayList<>();
    private static final List<Material> HeadList = new ArrayList<>();
    private static final List<Material> ChairList = new ArrayList<>();
    private static final List<Material> InvisibleItemFrameList = new ArrayList<>();

    public Recipes(ch.lepinat.shervin.stanley.main.Main plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);

        LightSourceList.add(Material.BARRIER);
        LightSourceList.add(Material.FERMENTED_SPIDER_EYE);
        LightSourceList.add(Material.BARRIER);
        LightSourceList.add(Material.FERMENTED_SPIDER_EYE);
        LightSourceList.add(Material.GLOWSTONE);
        LightSourceList.add(Material.FERMENTED_SPIDER_EYE);
        LightSourceList.add(Material.BARRIER);
        LightSourceList.add(Material.FERMENTED_SPIDER_EYE);
        LightSourceList.add(Material.BARRIER);

        FlySoupList.add(Material.DIAMOND_BLOCK);
        FlySoupList.add(Material.PHANTOM_MEMBRANE);
        FlySoupList.add(Material.DIAMOND_BLOCK);
        FlySoupList.add(Material.FEATHER);
        FlySoupList.add(Material.MUSHROOM_STEW);
        FlySoupList.add(Material.FEATHER);
        FlySoupList.add(Material.DIAMOND_BLOCK);
        FlySoupList.add(Material.FIREWORK_ROCKET);
        FlySoupList.add(Material.DIAMOND_BLOCK);

        KnockbackStickList.add(Material.DIAMOND_SWORD);
        KnockbackStickList.add(Material.NETHER_STAR);
        KnockbackStickList.add(Material.STICKY_PISTON);
        KnockbackStickList.add(Material.BLAZE_ROD);
        KnockbackStickList.add(Material.STICK);
        KnockbackStickList.add(Material.BLAZE_ROD);
        KnockbackStickList.add(Material.DIAMOND);
        KnockbackStickList.add(Material.LAVA_BUCKET);
        KnockbackStickList.add(Material.DIAMOND);

        HeadList.add(Material.LEATHER);
        HeadList.add(Material.ROTTEN_FLESH);
        HeadList.add(Material.LEATHER);
        HeadList.add(Material.ENDER_EYE);
        HeadList.add(Material.WHITE_WOOL);
        HeadList.add(Material.ENDER_EYE);
        HeadList.add(Material.LEATHER);
        HeadList.add(Material.BONE);
        HeadList.add(Material.LEATHER);

        ChairList.add(Material.BARRIER);
        ChairList.add(Material.BARRIER);
        ChairList.add(Material.BARRIER);
        ChairList.add(Material.BARRIER);
        ChairList.add(Material.MINECART);
        ChairList.add(Material.BARRIER);
        ChairList.add(Material.BARRIER);
        ChairList.add(Material.OAK_SLAB);
        ChairList.add(Material.BARRIER);

        InvisibleItemFrameList.add(Material.BARRIER);
        InvisibleItemFrameList.add(Material.FERMENTED_SPIDER_EYE);
        InvisibleItemFrameList.add(Material.BARRIER);
        InvisibleItemFrameList.add(Material.FERMENTED_SPIDER_EYE);
        InvisibleItemFrameList.add(Material.ITEM_FRAME);
        InvisibleItemFrameList.add(Material.FERMENTED_SPIDER_EYE);
        InvisibleItemFrameList.add(Material.BARRIER);
        InvisibleItemFrameList.add(Material.FERMENTED_SPIDER_EYE);
        InvisibleItemFrameList.add(Material.BARRIER);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();

        if (inventory == RecipeInventory) {
            event.setCancelled(true);
            if(clicked.equals(Items.getLightSource())){
                createRecipeInventory("LightSource", LightSourceList, Items.getLightSource());
                player.openInventory(CraftingInventory);
            }else if (clicked.equals(Items.getFlySoup())){
                createRecipeInventory("FlySoup", FlySoupList, Items.getFlySoup());
                player.openInventory(CraftingInventory);
            }else if (clicked.equals(Items.getKnockBackStick())){
                createRecipeInventory("KnockBackStick", KnockbackStickList, Items.getKnockBackStick());
                player.openInventory(CraftingInventory);
            }else if (clicked.equals(Items.createHead())){
                createRecipeInventory("Head", HeadList, Items.createHead());
                player.openInventory(CraftingInventory);
            }else if (clicked.equals(Items.getChair())){
                createRecipeInventory("Chair", ChairList, Items.getChair());
                player.openInventory(CraftingInventory);
            }else if (clicked.equals(Items.getInvisibleItemFrame())){
                createRecipeInventory("InvisibleItemFrame", LightSourceList, Items.getInvisibleItemFrame());
                player.openInventory(CraftingInventory);
            }
        }
        if (inventory == CraftingInventory){
            event.setCancelled(true);
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player p) {
            RecipeInventory = Bukkit.createInventory(null, 3*9, RecipeInvName);

            RecipeInventory.setItem(10, Items.getLightSource());
            RecipeInventory.setItem(11, Items.getChair());
            RecipeInventory.setItem(12, Items.getFlySoup());
            RecipeInventory.setItem(14, Items.getKnockBackStick());
            RecipeInventory.setItem(15, Items.createHead());
            RecipeInventory.setItem(16, Items.getInvisibleItemFrame());

            p.openInventory(RecipeInventory);
        } else {
            sender.sendMessage("§cDu musst ein §bSpieler §csein um diesen Command zu benutzen§7.");
        }
        return false;
    }

    private static void createItemStack(Material material, Inventory inv, int Slot, String name, String lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        if(lore != null){
            ArrayList<String> Lore = new ArrayList<String>();
            Lore.add(lore);
            meta.setLore(Lore);
        }
        item.setItemMeta(meta);

        inv.setItem(Slot, item);
    }

    private void createRecipeInventory(String itemName, List<Material> materialList, ItemStack item){
        /*CraftingInventory = Bukkit.createInventory(null, 6*9, CraftingInvName + itemName);

        createItemStack(materialList.get(0), CraftingInventory, 11, "", null);
        createItemStack(materialList.get(1), CraftingInventory, 12, "", null);
        createItemStack(materialList.get(2), CraftingInventory, 13, "", null);
        createItemStack(materialList.get(3), CraftingInventory, 20, "", null);
        createItemStack(materialList.get(4), CraftingInventory, 21, "", null);
        createItemStack(materialList.get(5), CraftingInventory, 22, "", null);
        createItemStack(materialList.get(6), CraftingInventory, 29, "", null);
        createItemStack(materialList.get(7), CraftingInventory, 30, "", null);
        createItemStack(materialList.get(8), CraftingInventory, 31, "", null);
        CraftingInventory.setItem(24, item);
        CraftingInventory.setItem(45, BackButton);
         */

        CraftingInventory = Bukkit.createInventory(null, InventoryType.WORKBENCH, CraftingInvName + itemName);

        createItemStack(materialList.get(0), CraftingInventory, 1, "", null);
        createItemStack(materialList.get(1), CraftingInventory, 2, "", null);
        createItemStack(materialList.get(2), CraftingInventory, 3, "", null);
        createItemStack(materialList.get(3), CraftingInventory, 4, "", null);
        createItemStack(materialList.get(4), CraftingInventory, 5, "", null);
        createItemStack(materialList.get(5), CraftingInventory, 6, "", null);
        createItemStack(materialList.get(6), CraftingInventory, 7, "", null);
        createItemStack(materialList.get(7), CraftingInventory, 8, "", null);
        createItemStack(materialList.get(8), CraftingInventory, 9, "", null);
        CraftingInventory.setItem(0, item);
    }


}
