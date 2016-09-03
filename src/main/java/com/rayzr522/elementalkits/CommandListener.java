
package com.rayzr522.elementalkits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.rayzr522.elementalkits.kits.Kit;
import com.rayzr522.elementalkits.kits.KitFrost;
import com.rayzr522.elementalkits.kits.KitInferno;
import com.rayzr522.elementalkits.kits.KitVortex;
import com.rayzr522.elementalkits.utils.IconMenu;
import com.rayzr522.elementalkits.utils.IconMenu.OptionClickEvent;
import com.rayzr522.elementalkits.utils.Msg;
import com.rayzr522.elementalkits.utils.item.ItemUtils;

public class CommandListener implements CommandExecutor {

	private ElementalKits			plugin;
	private IconMenu				menu;

	private static final ItemStack	KIT_INFERNO		= ItemUtils.makeItem("fireball, named &6Kit: &cInferno, with lore &9Look at the book below for a description");
	private static final ItemStack	KIT_FROST		= ItemUtils.makeItem("ice, named &6Kit: &bFrost, with lore &9Look at the book below for a description");
	private static final ItemStack	KIT_VORTEX		= ItemUtils.makeItem("emerald, named &6Kit: &aVortex, with lore &9Look at the book below for a description");

	private static final ItemStack	KIT_NONE		= ItemUtils.makeItem("redstone block, named &cRemove Kit");
	private static final ItemStack	SELECTED_KIT	= ItemUtils.makeItem("gold nugget, named &eSelected Kit");

	private ItemStack				blank			= new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);

	public CommandListener(ElementalKits instance) {
		this.plugin = instance;
		menu = new IconMenu(ChatColor.BLUE + "Select a Kit", 54, this::itemClick, instance);

		ItemUtils.setName(blank, " ");

		int[] blanks = new int[54];

		for (int i = 0; i < 54; i++) {
			blanks[i] = i;
		}

		menu.setOptions(blanks, blank);

		menu.setOption(1, 1, KIT_INFERNO);
		menu.setOption(1, 4, KIT_FROST);
		menu.setOption(1, 7, KIT_VORTEX);

		updateDescriptions();

		menu.setOption(4, 4, KIT_NONE);

	}

	private void updateDescriptions() {

		menu.setOption(2, 1, Config.DESCRIPTION_INFERNO);
		menu.setOption(2, 4, Config.DESCRIPTION_FROST);
		menu.setOption(2, 7, Config.DESCRIPTION_VORTEX);

	}

	private void updateMenu(Player p) {

		int id = Players.get(p);

		menu.setOption(0, 1, blank);
		menu.setOption(0, 4, blank);
		menu.setOption(0, 7, blank);

		if (id == KitInferno.ID) {
			menu.setOption(0, 1, SELECTED_KIT);
		} else if (id == KitFrost.ID) {
			menu.setOption(0, 4, SELECTED_KIT);
		} else if (id == KitVortex.ID) {
			menu.setOption(0, 7, SELECTED_KIT);
		}

	}

	public void itemClick(OptionClickEvent event) {

		Player p = event.getPlayer();
		event.setWillClose(true);

		if (event.getItem() == KIT_INFERNO) {

			int id = Players.get(p);
			if (id == KitInferno.ID) {
				Msg.send(p, "already-selected", "&cInferno");
				return;
			}

			Kits.set(p, KitInferno.ID);
			Msg.send(p, "kit-selected", "&cInferno");

		} else if (event.getItem() == KIT_FROST) {

			int id = Players.get(p);
			if (id == KitFrost.ID) {
				Msg.send(p, "already-selected", "&bFrost");
				return;
			}

			Kits.set(p, KitFrost.ID);
			Msg.send(p, "kit-selected", "&bFrost");

		} else if (event.getItem() == KIT_VORTEX) {

			int id = Players.get(p);
			if (id == KitVortex.ID) {
				Msg.send(p, "already-selected", "&aVortex");
				return;
			}

			Kits.set(p, KitVortex.ID);
			Msg.send(p, "kit-selected", "&aVortex");

		} else if (event.getItem() == KIT_NONE) {

			int id = Players.get(p);
			if (id == 0) {
				Msg.send(p, "no-class");
				return;
			}

			Kit kit = Kits.getKit(id);
			kit.onKitRemove(p);
			Players.set(p, 0);
			Msg.send(p, "kit-removed");

		} else { // ONLY if they clicked on none of the buttons
			event.setWillClose(false);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("ekit")) {

			if (!(sender instanceof Player)) {
				Msg.send(sender, "only-players", label);
				return true;
			}

			Player p = (Player) sender;

			ekit(p, args);

		}

		if (cmd.getName().equalsIgnoreCase("elementalkits")) {

			elementalkits(sender, args);

		}

		return true;
	}

	public void ekit(Player p, String[] args) {

		if (!p.hasPermission(Config.PERM_EKIT)) {
			Msg.send(p, "no-permission");
			return;
		}

		updateMenu(p);

		menu.open(p);

	}

	public void elementalkits(CommandSender p, String[] args) {

		if (!p.hasPermission(Config.PERM_MANAGE)) {
			Msg.send(p, "no-permission");
			return;
		}

		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				plugin.reloadConfig();
				plugin.load();
				updateDescriptions();
				Msg.send(p, "config-reloaded");
				return;
			}
		}

		Msg.sep(p);
		Msg.send(p, "plugin-info", plugin.versionString());
		Msg.send(p, "usage.elementalkits");
		Msg.sep(p);

	}

}
