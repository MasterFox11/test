package otic.foxy11.me.managers;

import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

public class PlayerManager {
	
	HashMap<String, List<Player>> lobbyDb = new HashMap<String, List<Player>>();
	HashMap<String, List<Player>> gameDb = new HashMap<String, List<Player>>();
	
	public class ArenaPlayerManager {
		public void addToLobby(String arena, Player target) {
			List<Player> players = lobbyDb.get(arena);
		    players.add(target);
		}
		
		public void removeFromLobby(String arena, Player target) {
			List<Player> players = lobbyDb.get(arena);
		    if (players.size() == 1) {
		    	lobbyDb.remove(arena);
		    }
		    players.remove(target);
		}
		
		public void addToArena(String arena, Player target) {
			List<Player> players = gameDb.get(arena);
		    players.add(target);
		}
		
		public void removeFromArena(String arena, Player target) {
			List<Player> players = gameDb.get(arena);
		    if (players.size() == 1) {
		    	gameDb.remove(arena);
		    }
		    players.remove(target);
		}
		
		public String getLobbyArena(Player target) {
			for (String lobby : lobbyDb.keySet()) {
				List<Player> players = getLobbyPlayers(lobby);
				for (Player pl : players) {
					if (pl.equals(target)) {
						return lobby;
					}
					continue;
				}
			}
			return null;
		}
		
		public String getGameArena(Player target) {
			for (String game : gameDb.keySet()) {
				List<Player> players = getGamePlayers(game);
				for (Player pl : players) {
					if (pl.equals(target)) {
						return game;
					}
					continue;
				}
			}
			return null;
		}
	}

	public ArenaPlayerManager managePlayers() {
		ArenaPlayerManager apm = new ArenaPlayerManager();
		return apm;
	}
	
	public List<Player> getLobbyPlayers(String arena) {
		List<Player> players = lobbyDb.get(arena);
		return players;
	}
	
	public List<Player> getGamePlayers(String arena) {
		List<Player> players = gameDb.get(arena);
		return players;
	}
}

