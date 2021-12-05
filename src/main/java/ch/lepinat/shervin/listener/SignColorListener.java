package ch.lepinat.shervin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.util.Map;

public class SignColorListener implements Listener {

    @EventHandler
    public void SignColor(SignChangeEvent e) {
        for (Map.Entry color: ChatColorListener.getAllColors().entrySet()) {
            for (int i = 0; i < e.lines().size(); i++) {
                e.setLine(i, e.getLine(i).replaceAll((String) color.getKey(), (String) color.getValue()));
            }
        }
    }

}
