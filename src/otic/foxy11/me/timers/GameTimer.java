package otic.foxy11.me.timers;

import java.util.HashMap;

import otic.foxy11.me.managers.GameManager;
import otic.foxy11.me.managers.PlayerManager;

public class GameTimer implements Runnable {
	
	PlayerManager player = new PlayerManager();
	GameManager game = new GameManager();

	public static HashMap<String, Integer> arenas = new HashMap<String, Integer>();
	
	public void run() {
		if (!(arenas.isEmpty())) {
			for (String arena : arenas.keySet()) {
				if (player.getGamePlayers(arena).size() >= 2) {
					int time = arenas.get(arena);
					if (time == 0) {
						game.stopGame(arena);
					}
					time--;
				}
				game.stopGame(arena);
			}
		}
	}
}
