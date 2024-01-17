package ch.lepinat.shervin.stanley.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.util.Map;

public class SignColorListener implements Listener {

    @EventHandler
    public void SignColor(SignChangeEvent e) {
        for (Map.Entry<String, String> color: ChatColorListener.getAllColors().entrySet()) {
            for (int i = 0; i < e.lines().size(); i++) {
                e.setLine(i, e.getLine(i).replaceAll(color.getKey(), color.getValue()));
            }
        }
    }

}
