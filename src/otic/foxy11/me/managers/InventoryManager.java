package otic.foxy11.me.managers;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

	HashMap<String, ItemStack[]> inventories = new HashMap<String, ItemStack[]>();
	
	public void saveInventory(Player target) {
		ItemStack[] items = target.getInventory().getContents();
		inventories.put(target.getName(), items);
	}
	
	public void loadInventory(Player target) {
		ItemStack[] items = inventories.get(target.getName());
		target.getInventory().setContents(items);
	}
	
	public void gameInventory(Player target) {
		ItemStack sword = new ItemStack(Material.GOLD_SWORD);
		ItemStack bow = new ItemStack(Material.BOW);
		ItemStack arrow = new ItemStack(Material.ARROW);
		target.getInventory().addItem(sword);
		target.getInventory().addItem(bow);
		target.getInventory().addItem(arrow);
	}
}
