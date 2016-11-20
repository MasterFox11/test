package otic.foxy11.me;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import otic.foxy11.me.cmd.MainCommand;
import otic.foxy11.me.events.ArrowHitEvent;
import otic.foxy11.me.events.DeathEvent;
import otic.foxy11.me.events.KillEvent;
import otic.foxy11.me.events.LeaveEvent;
import otic.foxy11.me.events.RespawnEvent;
import otic.foxy11.me.managers.ConfigManager;

public class Main extends JavaPlugin {
	
	private Plugin p;

	public void onEnable() {
		p = this;
		
		ConfigManager.getInstance().setup(p);
		this.getCommand("oitc").setExecutor(new MainCommand());
		
		Bukkit.getPluginManager().registerEvents(new ArrowHitEvent(), this);
		Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);
		Bukkit.getPluginManager().registerEvents(new KillEvent(), this);
		Bukkit.getPluginManager().registerEvents(new LeaveEvent(), this);
		Bukkit.getPluginManager().registerEvents(new RespawnEvent(), this);
	}
	
	public void onDisable() {
		p = null;
	}
}
