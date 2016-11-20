package otic.foxy11.me.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import otic.foxy11.me.Main;
import otic.foxy11.me.managers.InventoryManager;

public class RespawnEvent implements Listener {
	
	final Plugin p = JavaPlugin.getPlugin(Main.class);
	
	InventoryManager inv = new InventoryManager();

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player target = e.getPlayer();
		if (target.hasMetadata("ingame")) {
			inv.gameInventory(target);
			target.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 2));
			target.setGameMode(GameMode.SPECTATOR);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
				public void run() {
					target.setGameMode(GameMode.SURVIVAL);
				}
			}, 200L);
		}
	}
}
