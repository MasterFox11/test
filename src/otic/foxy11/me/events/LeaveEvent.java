package otic.foxy11.me.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import otic.foxy11.me.managers.GameManager;
import otic.foxy11.me.managers.PlayerManager;

public class LeaveEvent implements Listener {

	GameManager gm = new GameManager();
	PlayerManager pm = new PlayerManager();
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player target = e.getPlayer();
		if (target.hasMetadata("inlobby")) {
			String lobby = pm.managePlayers().getLobbyArena(target);
			gm.leaveLobby(lobby, target);
		}
		else if (target.hasMetadata("ingame")) {
			String arena = pm.managePlayers().getGameArena(target);
			gm.leaveGame(arena, target);
		}
	}
}
