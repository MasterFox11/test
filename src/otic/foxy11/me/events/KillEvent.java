package otic.foxy11.me.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import otic.foxy11.me.managers.KillManager;

public class KillEvent implements Listener {
	
	KillManager km = new KillManager();

	@EventHandler
	public void onKill(EntityDamageByEntityEvent e) {
		Entity eDamager = e.getDamager();
		Entity eTarget = e.getEntity();
		if (eDamager instanceof Player && eTarget instanceof Player) {
			Player damager = (Player) e.getDamager();
			Player target = (Player) e.getEntity();
			if (damager.hasMetadata("ingame") && target.hasMetadata("ingame")) {
				if (target.isDead()) {
					int kills = km.getKills(damager);
					km.setKills(damager, kills + 1);
				}
			}
		}
	}
}
