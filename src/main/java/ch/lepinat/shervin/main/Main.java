package ch.lepinat.shervin.main;

import java.io.IOException;

import ch.lepinat.shervin.commands.*;
import ch.lepinat.shervin.crafting.ChairRecipe;
import ch.lepinat.shervin.exceptions.LeftException;
import ch.lepinat.shervin.exceptions.isNullException;
import ch.lepinat.shervin.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ch.lepinat.shervin.crafting.FlySoup;
import ch.lepinat.shervin.crafting.Head;
import ch.lepinat.shervin.crafting.KnockbackStick;

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

        new KnockbackStick().registerRecipes();
        new Head().registerRecipes();
        new FlySoup().registerRecipes();
        new ChairRecipe().registerRecipes();

        for (Player p : Bukkit.getOnlinePlayers()) {
            try {
                long timer = Config.getTimer(p.getUniqueId());
                SoupListener soupListener = new SoupListener();
                soupListener.flugTimer(p, timer / 1000);
            } catch (LeftException | isNullException ignored) {
            }
        }

        getCommand("gm").setExecutor(new gamemodeCMD());
        getCommand("cc").setExecutor(new ccCMD());
        getCommand("heal").setExecutor(new HealCMD());
        getCommand("fly").setExecutor(new FlyCMD());
        getCommand("setname").setExecutor(new DisplaynameCMD());
        getCommand("resetname").setExecutor(new resetnameCMD());
        getCommand("flyspeed").setExecutor(new FlyspeedCMD());
        getCommand("getflyspeed").setExecutor(new getflyspeedCMD());
        getCommand("enchantall").setExecutor(new EnchantAllCMD());
        getCommand("author").setExecutor(new ChangeAuthorCMD());
        getCommand("openinv").setExecutor(new OpenInventoryCMD());
        getCommand("flytime").setExecutor(new flytimeCMD());
        getCommand("getflysoup").setExecutor(new getFlySoupCMD());
        getCommand("getchair").setExecutor(new getChairCMD());
        getCommand("head").setExecutor(new HeadCMD());
        getCommand("gethead").setExecutor(new getHeadCMD());
        getCommand("stats").setExecutor(new StatsCMD());
        getCommand("repair").setExecutor(new RepairCMD());
        getCommand("burn").setExecutor(new burnCommand());

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
