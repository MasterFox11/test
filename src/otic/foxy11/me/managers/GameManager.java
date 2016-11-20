package otic.foxy11.me.managers;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import otic.foxy11.me.Main;
import otic.foxy11.me.timers.GameTimer;
import otic.foxy11.me.timers.LobbyTimer;

public class GameManager {
	
	PlayerManager player = new PlayerManager();
	ArenaManager am = new ArenaManager();
	InventoryManager inv = new InventoryManager();
	KillManager km = new KillManager();
	
	final Plugin p = JavaPlugin.getPlugin(Main.class);

	HashMap<String, Integer> arenas = new HashMap<String, Integer>();
	HashMap<String, Integer> lobbies = new HashMap<String, Integer>();
	
	public void startLobby(String arena) {
		if (lobbies.isEmpty()) {
			lobbies.put(arena, 10);
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(p, new LobbyTimer(), 20L, 20L);
		}
		lobbies.put(arena, 10);
	}
	
	public void stopLobby(String arena) {
		lobbies.remove(arena);
	}
	
	public void joinLobby(String arena, Player target) {
		List<Player> players = player.getLobbyPlayers(arena);
		if (players.size() <= 8 || players == null) {
			inv.saveInventory(target);
			target.getInventory().clear();
			target.setMetadata("inlobby", new FixedMetadataValue(p, "456"));
			player.managePlayers().addToLobby(arena, target);
			target.teleport(am.spawnpoint().getLobby(arena));
			ChatManager.info("To leave, execute /oitc leave", target);
			if (players.size() >= 4) {
				LobbyTimer.lobbies.put(arena, 10);
			}
		}
		ChatManager.warning("That arena is full!", target);
	}
	
	public void leaveLobby(String arena, Player target) {
		List<Player> players = player.getLobbyPlayers(arena);
		for (Player pl : players) {
			if (pl == target) {
				pl.removeMetadata("inlobby", p);
				pl.getInventory().clear();
				inv.loadInventory(pl);
				pl.performCommand("spawn");
				player.managePlayers().removeFromLobby(arena, pl);
				players.remove(pl);
				break;
			}
			continue;
		} 
	}
	
	public void startGame(String arena) {
		if (arenas.isEmpty()) {
			List<Player> players = player.getGamePlayers(arena);
			for (Player pl : players) {
				pl.setMetadata("ingame", new FixedMetadataValue(p, "345"));
				pl.getInventory().clear();
				inv.gameInventory(pl);
				player.managePlayers().removeFromLobby(arena, pl);
				player.managePlayers().addToArena(arena, pl);
				pl.teleport(am.spawnpoint().getArena(arena));
				km.setKills(pl, 0);
				break;
			}
			arenas.put(arena, 300);
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(p, new GameTimer(), 20L, 20L);
		}
		List<Player> players = player.getGamePlayers(arena);
		for (Player pl : players) {
			pl.setMetadata("ingame", new FixedMetadataValue(p, "345"));
			pl.getInventory().clear();
			inv.gameInventory(pl);
			player.managePlayers().removeFromLobby(arena, pl);
			player.managePlayers().addToArena(arena, pl);
			pl.teleport(am.spawnpoint().getArena(arena));
			km.setKills(pl, 0);
			break;
		}
		arenas.put(arena, 300);
	}
	 
	public void leaveGame(String arena, Player target) {
		List<Player> players = player.getGamePlayers(arena);
		for (Player pl : players) {
			if (pl == target) {
				km.removePlayer(pl);
				pl.removeMetadata("ingame", p);
				player.managePlayers().removeFromArena(arena, pl);
				pl.getInventory().clear();
				inv.loadInventory(pl);
				pl.performCommand("spawn");
				players.remove(pl);
				break;
			}
		}
	}
	
	public void stopGame(String arena) {
		List<Player> players = player.getGamePlayers(arena);
		for (Player pl : players) {
			km.removePlayer(pl);
			pl.removeMetadata("ingame", p);
			player.managePlayers().removeFromArena(arena, pl);
			pl.getInventory().clear();
			inv.loadInventory(pl);
			pl.performCommand("spawn");
			ChatManager.warning("The match ended!", pl);
			players.remove(pl);
			break;
		}
	}
}
