package ch.lepinat.shervin.stanley.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent Death) {
        Player p = Death.getEntity();
        Player k = Death.getEntity().getKiller();
        Death.getEntity();
        if (p.getLastDamageCause().getCause() == DamageCause.DROWNING) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cist ertrunken!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.BLOCK_EXPLOSION) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cwurde in die Luft gesprengt!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.ENTITY_ATTACK) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cwurde von " + k.getType().toString().toUpperCase() + " get§tet!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.FALL) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cist tief gefallen!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.FALLING_BLOCK) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §chat einen Ziegelstein auf dem Kopf bekommen!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.FIRE) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cist verbrannt wie mein Br§tchen :(!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.FIRE_TICK) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cist verbrannt wie mein Br§tchen :(!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.LAVA) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §chat versucht in Lava zu schwimmen! §bhaha"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.LIGHTNING) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cwurde vom Blitz getroffen!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.MAGIC) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cwurde verzaubet!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.POISON) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cwurde vergiftet!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.PROJECTILE) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cwurde abgeschossen!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.STARVATION) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cist verhungert!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.SUFFOCATION) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cist erstikt!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.SUICIDE) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §chat selbstmord begangen!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.THORNS) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cist an Dornen gestorben!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.VOID) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cist aus der Welt gefallen!"));
        } else if (p.getLastDamageCause().getCause() == DamageCause.WITHER) {
            Death.deathMessage(Component.text("§7" + p.getName() + " §cist vom Wither get§t worden!"));
        }
    }
}
