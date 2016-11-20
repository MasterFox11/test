package otic.foxy11.me.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ArenaManager {
	
	ConfigManager conf = ConfigManager.getInstance();
	
	public class SpawnType {
		public void setArena(String arena, Player target) {
			conf.getConfig().set("Oitc.arenas." + arena + ".gamespawn.world", target.getWorld().getName());
			conf.getConfig().set("Oitc.arenas." + arena + ".gamespawn.x", target.getLocation().getX());
			conf.getConfig().set("Oitc.arenas." + arena + ".gamespawn.y", target.getLocation().getY());
			conf.getConfig().set("Oitc.arenas." + arena + ".gamespawn.z", target.getLocation().getZ());
			conf.saveConfig();
		}
		
		public Location getArena(String arena) {
			World w = Bukkit.getWorld(conf.getConfig().getString("Oitc.arenas." + arena + ".gamespawn.world"));
			double x = conf.getConfig().getDouble("Oitc.arenas." + arena + ".gamespawn.x");
			double y = conf.getConfig().getDouble("Oitc.arenas." + arena + ".gamespawn.y");
			double z = conf.getConfig().getDouble("Oitc.arenas." + arena + ".gamespawn.z");
			Location loc = new Location(w, x, y, z);
			return loc;
		}
		
		public void setLobby(String arena, Player target) {
			conf.getConfig().set("Oitc.arenas." + arena + ".lobbyspawn.world", target.getWorld().getName());
			conf.getConfig().set("Oitc.arenas." + arena + ".lobbyspawn.x", target.getLocation().getX());
			conf.getConfig().set("Oitc.arenas." + arena + ".lobbyspawn.y", target.getLocation().getY());
			conf.getConfig().set("Oitc.arenas." + arena + ".lobbyspawn.z", target.getLocation().getZ());
			conf.saveConfig();
		}
		
		public Location getLobby(String arena) {
			World w = Bukkit.getWorld(conf.getConfig().getString("Oitc.arenas." + arena + ".lobbyspawn.world"));
			double x = conf.getConfig().getDouble("Oitc.arenas." + arena + ".lobbyspawn.x");
			double y = conf.getConfig().getDouble("Oitc.arenas." + arena + ".lobbyspawn.y");
			double z = conf.getConfig().getDouble("Oitc.arenas." + arena + ".lobbyspawn.z");
			Location loc = new Location(w, x, y, z);
			return loc;
		}
	}
	
	public void createArena(String name, Player target) {
		if (conf.getConfig().contains("Oitc.arenas." + name)) {
			ChatManager.warning("That arena already exists!", target);
		}
		conf.getConfig().set("Oitc.arenas." + name, name);
		ChatManager.pass("Created " + name + " with no errors!", target);
		conf.saveConfig();
	}
	
	public void deleteArena(String name, Player target) {
		if (conf.getConfig().contains("Oitc.arenas." + name)) {
			conf.getConfig().set("Oitc.arenas." + name, null);
			conf.saveConfig();
			ChatManager.pass("Deleted " + name + " with no errors!", target);
		}
		ChatManager.warning(name + " does not exist!", target);
	}

	public boolean isArenaValid(String arena) {
		if (conf.getConfig().contains("Oitc.arenas." + arena)) {
			return true;
		}
		return false;
	}
	
	public SpawnType spawnpoint() {
		SpawnType s = new SpawnType();
		return s;
	}
}
