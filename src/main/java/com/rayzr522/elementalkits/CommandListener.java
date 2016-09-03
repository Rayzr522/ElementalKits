
package com.rayzr522.elementalkits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.rayzr522.elementalkits.utils.IconMenu;
import com.rayzr522.elementalkits.utils.IconMenu.OptionClickEvent;
import com.rayzr522.elementalkits.utils.item.ItemUtils;

import net.md_5.bungee.api.ChatColor;

public class CommandListener implements CommandExecutor {

	private ElementalKits			plugin;
	private IconMenu				menu;

	private static final ItemStack	CLASS_INFERNO	= ItemUtils.makeItem("fireball, named &eClass: &cInferno, with lore &9Look at the book below for a description");
	private static final ItemStack	CLASS_FROST		= ItemUtils.makeItem("ice, named &eClass: &bFrost, with lore &9Look at the book below for a description");
	private static final ItemStack	CLASS_VORTEX	= ItemUtils.makeItem("emerald, named &eClass: &aVortex, with lore &9Look at the book below for a description");
	private static final ItemStack  CLASS_NONE      = ItemUtils.makeItem("redstone block, named &cRemove Class");
	
	public CommandListener(ElementalKits instance) {
		this.plugin = instance;
		menu = new IconMenu(ChatColor.BLUE + "Select a Kit", 54, this::itemClick, instance);
		// Put in the CLASS_<SOMETHING> items and books and stuff
	}

	public void itemClick(OptionClickEvent event) {
		event.setWillClose(true);

		if (event.getItem() == CLASS_INFERNO) {

		} else if (event.getItem() == CLASS_FROST) {

		} else if (event.getItem() == CLASS_VORTEX) {

		} else if (event.getItem() == CLASS_NONE) {
		
		} else { // ONLY if they clicked on none of the buttons
			event.setWillClose(false);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use these commands!");
			return true;
		}

		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("ekit")) {

			ekit(p, args);

		}

		if (cmd.getName().equalsIgnoreCase("elementalkits")) {

			elementalkits(p, args);

		}

		return true;
	}

	public void ekit(Player p, String[] args) {

		menu.open(p);

	}

	public void elementalkits(Player p, String[] args) {

		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				plugin.reloadConfig();
				plugin.load();
			}
		}

	}

}
