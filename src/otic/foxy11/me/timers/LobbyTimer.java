package otic.foxy11.me.timers;

import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import otic.foxy11.me.managers.ChatManager;
import otic.foxy11.me.managers.GameManager;
import otic.foxy11.me.managers.PlayerManager;

public class LobbyTimer implements Runnable {
	
	PlayerManager player = new PlayerManager();
	GameManager game = new GameManager();
	
	public static HashMap<String, Integer> lobbies = new HashMap<String, Integer>();
	
	@Override
	public void run() {
		if (!(lobbies.isEmpty())) {
			for (String arena : lobbies.keySet()) {
				List<Player> players = player.getLobbyPlayers(arena);
				if (player.getLobbyPlayers(arena).size() >= 4) {
					int time = lobbies.get(arena);
					if (time == 0) {
						game.startGame(arena);
					}
					time--;
					ChatManager.massInfo("Game starting in " + time, players);
				}
				game.stopLobby(arena);
				ChatManager.massInfo("Cannot start due to insuffecient players!", players);
			}
		}
	}
}
