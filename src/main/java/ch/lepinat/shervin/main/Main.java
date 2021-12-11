package ch.lepinat.shervin.main;

import ch.lepinat.shervin.commands.*;
import ch.lepinat.shervin.crafting.ChairRecipe;
import ch.lepinat.shervin.crafting.FlySoup;
import ch.lepinat.shervin.crafting.KnockBackStick;
import ch.lepinat.shervin.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

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

        new KnockBackStick().registerRecipes();
        new ch.lepinat.shervin.crafting.Head().registerRecipes();
        new FlySoup().registerRecipes();
        new ChairRecipe().registerRecipes();

        getCommand("gm").setExecutor(new gameMode());
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
        getCommand("head").setExecutor(new Head());
        getCommand("gethead").setExecutor(new GetHead());
        getCommand("stats").setExecutor(new Stats());
        getCommand("repair").setExecutor(new Repair());
        getCommand("burn").setExecutor(new Burn());

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
}
