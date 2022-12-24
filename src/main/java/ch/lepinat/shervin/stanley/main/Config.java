package ch.lepinat.shervin.stanley.main;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import ch.lepinat.shervin.stanley.exceptions.LeftException;
import ch.lepinat.shervin.stanley.exceptions.isNullException;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config extends JavaPlugin {
    private static final File ConfigFile = new File("plugins/Stanley", "config.yml");
    private static final YamlConfiguration config = YamlConfiguration.loadConfiguration(ConfigFile);
    private static final String flySoupKey = "FlySoup.";
    private static final String chairKey = "ChairLocation.";
    private static final String joinKey = "Join.";

    public static void save() throws IOException {
        config.save(ConfigFile);
    }

    public static void setJoint(String name) throws IOException {
        config.set(joinKey + name, true);
        save();
    }

    public static boolean isAlreadyJoint(String name) {
        return config.getBoolean(joinKey + name);
    }

    public static long setTimer(UUID uuid, long time) throws IOException {
        long timer = new Date(Instant.now().toEpochMilli() + time * 1000).getTime();
        config.set(flySoupKey + uuid, timer);
        save();
        return timer;
    }

    public static void removeTimer(UUID uuid) throws IOException {
        config.set(flySoupKey + uuid, null);
        save();
    }

    public static long getTimer(UUID uuid) throws LeftException, isNullException {
        String info = config.getString(flySoupKey + uuid);
        if (info != null) {
            Bukkit.broadcast(Component.text(String.valueOf(!info.equals("leave"))));
            if (!info.equals("leave")) {
                Date now = new Date(Instant.now().toEpochMilli());
                Date timer = new Date(Long.parseLong(config.getString(flySoupKey + uuid)));
                return timer.getTime() - now.getTime();
            } else {
                throw new LeftException();
            }
        }
        throw new isNullException();
    }

    public static long getstarttime(UUID uuid) {
        if (config.getString(flySoupKey + uuid) != null) {
            return new Date(Long.parseLong(config.getString(flySoupKey + uuid))).getTime();
        }
        return -1;
    }

    public static void isLeftetWhileFlying(UUID uuid) {
        config.set(flySoupKey + uuid, "leave");
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
