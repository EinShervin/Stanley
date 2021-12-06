package ch.lepinat.shervin.main;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Config extends JavaPlugin {
    private static final File ConfigFile = new File("plugins/Stanley", "config.yml");
    private static final YamlConfiguration config = YamlConfiguration.loadConfiguration(ConfigFile);
    private static final String flySoupKey = "FlySoup.";
    private static final String chairKey = "ChairLocation.";

    public static void save() throws IOException {
        config.save(ConfigFile);
    }

    public static void setTimer(String uuid, long time) throws IOException {
        long timer = new Date(Instant.now().toEpochMilli() + time * 1000).getTime();
        config.set(flySoupKey + uuid, timer);
        save();
    }

    public static void removeTimer(String uuid) throws IOException {
        config.set(flySoupKey + uuid, null);
        save();
    }

    public static Long getTimer(String uuid) {
        String info = config.getString(flySoupKey + uuid);
        if (info != null) {
            Bukkit.broadcast(Component.text(String.valueOf(!info.equals("leave"))));
            if (!info.equals("leave")) {
                Date now = new Date(Instant.now().toEpochMilli());
                Date timer = new Date(Long.parseLong(config.getString(flySoupKey + uuid)));
                return timer.getTime() - now.getTime();
            }
        }
        return null;
    }

    public static long getstarttime(String uuid) {
        if (config.getString(flySoupKey + uuid) != null) {
            return new Date(Long.parseLong(config.getString(flySoupKey + uuid))).getTime();
        }
        return -1;
    }

    public static void setChairLocation(Location location, UUID uuid) throws IOException {
        config.set(chairKey + location.getWorld() + location.getBlockX() + location.getBlockY() + location.getBlockZ(), uuid.toString());
        save();
    }

    public static void removeChairLocation(Location location) throws IOException {
        if (config.getString(chairKey + location.getWorld() + location.getBlockX() + location.getBlockY() + location.getBlockZ()) != null) {
            config.set(chairKey + location.getWorld() + location.getBlockX() + location.getBlockY() + location.getBlockZ(), null);
            save();
        }
    }

    public static boolean getChairLocation(Location location) {
        return config.getString(chairKey + location.getWorld() + location.getBlockX() + location.getBlockY() + location.getBlockZ()) != null;
    }
}
