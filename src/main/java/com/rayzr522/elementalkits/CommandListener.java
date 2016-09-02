
package com.rayzr522.elementalkits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.rayzr522.elementalkits.kits.Kit;

import net.md_5.bungee.api.ChatColor;

public class CommandListener implements CommandExecutor {

	ElementalKits plugin;

	public CommandListener(ElementalKits instance) {
		this.plugin = instance;
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

		if (args.length == 1) {

			Kit kit = Kits.getKit(Integer.parseInt(args[0]));
			if (kit == null) {
				p.sendMessage(ChatColor.RED + "That kit does not exist!");
				return;
			}

			p.getInventory().addItem((ItemStack[]) kit.getItems().toArray());

		}

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
