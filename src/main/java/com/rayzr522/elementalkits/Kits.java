
package com.rayzr522.elementalkits;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.rayzr522.elementalkits.kits.Kit;

public class Kits {

	private static HashMap<Integer, Kit> kits = new HashMap<>();

	public static int addKit(Kit kit) {
		int id = kits.size() + 1;
		kits.put(id, kit);
		return id;
	}

	public static Kit getKit(int id) {
		return kits.get(id);
	}

	public static void set(Player p, int id) {

		Kit current = getKit(Players.get(p));
		if (current != null) {
			current.onKitRemove(p);
		}

		Kit kit = getKit(id);
		if (kit != null) {
			p.getInventory().addItem((ItemStack[]) kit.getItems().toArray());
		}
		Players.set(p, id);

	}

}
