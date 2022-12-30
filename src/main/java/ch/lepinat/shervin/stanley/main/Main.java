package ch.lepinat.shervin.stanley.main;

import ch.lepinat.shervin.stanley.commands.*;
import ch.lepinat.shervin.stanley.commands.Head;
import ch.lepinat.shervin.stanley.crafting.*;
import ch.lepinat.shervin.stanley.exceptions.LeftException;
import ch.lepinat.shervin.stanley.exceptions.isNullException;
import ch.lepinat.shervin.stanley.helper.GlowEnchant;
import ch.lepinat.shervin.stanley.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.lang.reflect.Field;

public class Main extends JavaPlugin {

    private static Main plugin;

    public void onEnable() {
        plugin = this;
        this.getLogger().info("Das Plugin funktioniert!");
        try {
            Config.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveDefaultConfig();

        registerGlowEnchantment();

        new KnockBackStick().registerRecipes();
        new HeadCrafting().registerRecipes();
        new FlySoup().registerRecipes();
        new ChairRecipe().registerRecipes();
        new LightSource().registerRecipes();
        new InvisibleItemFrame().registerRecipes();

        for (Player p : Bukkit.getOnlinePlayers()) {
            try {
                long timer = Config.getTimer(p.getUniqueId());
                SoupListener soupListener = new SoupListener();
                soupListener.flugTimer(p, timer / 1000);
            } catch (LeftException | isNullException ignored) {
            }
        }

        getCommand("gm").setExecutor(new GameMode());
        getCommand("cc").setExecutor(new ClearChat());
        getCommand("heal").setExecutor(new Heal());
        getCommand("fly").setExecutor(new Fly());
        getCommand("setname").setExecutor(new SetItemName());
        getCommand("resetname").setExecutor(new ResetName());
        getCommand("flyspeed").setExecutor(new FlySpeed());
        getCommand("getflyspeed").setExecutor(new GetFlySpeed());
        getCommand("enchantall").setExecutor(new EnchantAll());
        getCommand("author").setExecutor(new ChangeAuthor());
        getCommand("openinv").setExecutor(new OpenInventory());
        getCommand("flytime").setExecutor(new GetFlyTime());
        getCommand("getflysoup").setExecutor(new GetFlySoup());
        getCommand("getchair").setExecutor(new GetChair());
        getCommand("head").setExecutor(new ch.lepinat.shervin.stanley.commands.Head());
        getCommand("gethead").setExecutor(new GetHead());
        getCommand("stats").setExecutor(new Stats());
        getCommand("repair").setExecutor(new Repair());
        getCommand("burn").setExecutor(new Burn());
        getCommand("recipes").setExecutor(new Recipes(this));

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new LeftListener(), this);
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new SoupListener(), this);
        pluginManager.registerEvents(new KnockbackListener(), this);
        pluginManager.registerEvents(new SittingListener(), this);
        pluginManager.registerEvents(new ChatColorListener(), this);
        pluginManager.registerEvents(new SignColorListener(), this);
        //pluginManager.registerEvents(new EhrenlosListener(), this);
    }

    public static Main getPlugin() {
        return plugin;
    }

    public void registerGlowEnchantment(){
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            GlowEnchant glow = new GlowEnchant(new NamespacedKey(this, "70"));
            Enchantment.registerEnchantment(glow);
        }
        catch (IllegalArgumentException e){
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
