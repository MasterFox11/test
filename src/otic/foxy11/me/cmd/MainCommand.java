package otic.foxy11.me.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import otic.foxy11.me.managers.ArenaManager;
import otic.foxy11.me.managers.ChatManager;
import otic.foxy11.me.managers.GameManager;
import otic.foxy11.me.managers.PlayerManager;

public class MainCommand implements CommandExecutor {
	
	ArenaManager am = new ArenaManager();
	GameManager gm = new GameManager();
	PlayerManager pm = new PlayerManager();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player executor = (Player) sender;
			if (cmd.getName().equals("oitc")) {
				if (args.length == 1) {
					if (args[0].equals("leave")) {
						if (executor.hasMetadata("inlobby")) {
							String lobby = pm.managePlayers().getLobbyArena(executor);
							gm.leaveLobby(lobby, executor);
							ChatManager.pass("You left the lobby!", executor);
							return true;
						}
						else if (executor.hasMetadata("ingame")) {
							String game = pm.managePlayers().getGameArena(executor);
							gm.leaveGame(game, executor);
							ChatManager.pass("You left the game!", executor);
							return true;
						}
						else {
							ChatManager.warning("Are you ingame or inlobby?", executor);
							return true;
						}
					}
					else if (args[0].equals("help")) {
						ChatManager.info("/oitc create <name> : creates a arena called <name>", executor);
						ChatManager.info("/oitc remove <name> : removes the arena called <name>", executor);
						ChatManager.info("/oitc setspawn <name> lobby:game : sets the spawn for game or lobby in arena <name>", executor);
						ChatManager.info("/oitc join <name> : joins the lobby for arena <name>", executor);
						ChatManager.info("/oitc leave : leaves the game or lobby", executor);
						return true;
					}
					else {
						ChatManager.error("Invalid command! Type /oitc help for help!", executor);
						return true;
					}
				}
				else if (args.length == 2) {
					if (args[0].equals("create")) {
						if (executor.hasPermission("oitc.admin")) {
							if (args[1] != null) {
								if (am.isArenaValid(args[1])) {
									ChatManager.error("That arena already exists!", executor);
									return true;
								}
								am.createArena(args[1], executor);
								return true;
							}
							ChatManager.warning("Please input a arena name!", executor);
							return true;
						}
						ChatManager.error("You do not have permission to execute this command!", executor);
						return true;
					}
					else if (args[0].equals("remove")) {
						if (executor.hasPermission("oitc.admin")) {
							if (args[1] != null) {
								if (am.isArenaValid(args[1])) {
									am.deleteArena(args[0], executor);
									return true;
								}
								ChatManager.error("That arena does not exist!", executor);
								return true;
							}
							ChatManager.warning("Please input a arena name!", executor);
							return true;
						}
						ChatManager.error("You do not have permission to execute this command!", executor);
						return true;
					}
					else if (args[0].equals("join")) {
						if (args[1] != null) {
							if (am.isArenaValid(args[1])) {
								gm.joinLobby(args[1], executor);
								return true;
							}
							ChatManager.error("That arena does not exist!", executor);
							return true;
						}
						ChatManager.warning("Please input a arena name!", executor);
						return true;
					}
					else {
						ChatManager.error("Invalid command! Type /oitc help for help!", executor);
						return true;
					}
				}
				else if (args.length == 3) {
					if (executor.hasPermission("oitc.admin")) {
						if (args[0].equals("setspawn")) {
							if (args[1] != null) {
								if (am.isArenaValid(args[1])) {
									if (args[2] != null) {
										if (args[2].equals("lobby")) {
											am.spawnpoint().setLobby(args[1], executor);
											ChatManager.pass("Set lobby spawnpoint to your position!", executor);
											return true;
										}
										else if (args[2].equals("game")) {
											am.spawnpoint().setArena(args[1], executor);
											ChatManager.pass("Set game spawnpoint to your position!", executor);
											return true;
										}
										else {
											ChatManager.error("Invalid command! Type /oitc help for help!", executor);
											return true;
										}
									}
									else {
										ChatManager.error("Invalid command! Type /oitc help for help!", executor);
										return true;
									}
								}
								else {
									ChatManager.error("That arena does not exist!", executor);
									return true;
								}
							}
							else {
								ChatManager.warning("Please input a arena name!", executor);
								return true;
							}	
						}
						else {
							ChatManager.error("Invalid command! Type /oitc help for help!", executor);
							return true;
						}
					}
					ChatManager.error("You do not have permission to execute this command!", executor);
					return true;
				}
				else {
					ChatManager.error("Invalid command! Type /oitc help for help!", executor);
					return true;
				}
			}
		}
		return true;
	}

}
