package otic.foxy11.me.events;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import otic.foxy11.me.managers.KillManager;

public class ArrowHitEvent implements Listener {

	KillManager km = new KillManager();
	
	@EventHandler 
	public void onHit(EntityDamageByEntityEvent e) {
		Entity ePlayer = e.getEntity();
		Entity eDamager = e.getDamager();
		if (ePlayer instanceof Player && eDamager instanceof Arrow) {
		    Arrow damager = (Arrow)e.getDamager();
		    if (damager.hasMetadata("hit")) {
		    	e.setDamage(24);
		    }
		}
	}
}
