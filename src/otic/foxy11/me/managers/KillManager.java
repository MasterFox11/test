package otic.foxy11.me.managers;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class KillManager {

	HashMap<String, Integer> kills = new HashMap<String, Integer>();
	
	public int getKills(Player target) {
		int killCount = kills.get(target.getName());
		return killCount;
	}
	
	public void setKills(Player target, int killCount) {
		kills.put(target.getName(), killCount);
	}
	
	public void removePlayer(Player target) {
		kills.remove(target.getName());
	}
}
