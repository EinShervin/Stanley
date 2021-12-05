package ch.lepinat.shervin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;

public class ChatColorListener implements Listener {

    @EventHandler
    public void ChatListener(AsyncPlayerChatEvent e) {
        for (Map.Entry color: getAllColors().entrySet()) {
            e.setMessage(e.getMessage().replaceAll((String) color.getKey(), (String) color.getValue()));
        }
    }

    public static HashMap<String, String> getAllColors() {
        return new HashMap<>() {{
            put("&0", "§0");
            put("&1", "§1");
            put("&2", "§2");
            put("&3", "§3");
            put("&4", "§4");
            put("&5", "§5");
            put("&6", "§6");
            put("&7", "§7");
            put("&8", "§8");
            put("&9", "§9");
            put("&a", "§a");
            put("&b", "§b");
            put("&c", "§c");
            put("&d", "§d");
            put("&e", "§e");
            put("&f", "§f");
            put("&k", "§k");
            put("&l", "§l");
            put("&m", "§m");
            put("&n", "§n");
            put("&o", "§o");
            put("&r", "§r");
        }};
    }

}
