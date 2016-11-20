package otic.foxy11.me.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathEvent implements Listener {

	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		Entity eTarget = e.getEntity();
		if (eTarget instanceof Player) {
			Player target = (Player) e.getEntity();
			if (target.hasMetadata("ingame")) {
				e.getDrops().clear();
			}
		}
	}
}
