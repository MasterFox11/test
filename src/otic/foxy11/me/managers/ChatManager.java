package otic.foxy11.me.managers;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatManager {

	public static void pass(String message, Player reciever) {
		reciever.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "(!) " + ChatColor.RESET + ChatColor.GREEN + message);
	}
	
	public static void warning(String message, Player reciever) {
		reciever.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "(!) " + ChatColor.RESET + ChatColor.YELLOW + message);
	}
	
	public static void error(String message, Player reciever) {
		reciever.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "(!) " + ChatColor.RESET + ChatColor.DARK_RED + message);
	}
	
	public static void info(String message, Player reciever) {
		reciever.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "(!) " + ChatColor.RESET + ChatColor.AQUA + message);
	}
	
	public static void massPass(String message, List<Player> recievers) {
		int index = 1;
		int size = recievers.size();
		for (Player reciever : recievers) {
			if (index > size) {
				break;
			}
			reciever.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "(!) " + ChatColor.RESET + ChatColor.GREEN + message);
			index++;
			continue;
		}
	}
	
	public static void massWarning(String message, List<Player> recievers) {
		int index = 1;
		int size = recievers.size();
		for (Player reciever : recievers) {
			if (index > size) {
				break;
			}
			reciever.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "(!) " + ChatColor.RESET + ChatColor.YELLOW + message);
			index++;
			continue;
		}
	}
	
	public static void massError(String message, List<Player> recievers) {
		int index = 1;
		int size = recievers.size();
		for (Player reciever : recievers) {
			if (index > size) {
				break;
			}
			reciever.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "(!) " + ChatColor.RESET + ChatColor.DARK_RED + message);
			index++;
			continue;
		}
	}
	
	public static void massInfo(String message, List<Player> recievers) {
		int index = 1;
		int size = recievers.size();
		for (Player reciever : recievers) {
			if (index > size) {
				break;
			}
			reciever.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "(!) " + ChatColor.RESET + ChatColor.AQUA + message);
			index++;
			continue;
		}
	}
}
