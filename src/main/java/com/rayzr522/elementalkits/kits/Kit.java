
package com.rayzr522.elementalkits.kits;

import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.rayzr522.elementalkits.ElementalKits;

public abstract class Kit implements Listener {

	private ElementalKits plugin;

	public Kit(ElementalKits plugin) {

		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		init();

	}

	public abstract void init();

	public abstract List<ItemStack> getItems();

}
