
package com.rayzr522.elementalkits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

		}

		if (cmd.getName().equalsIgnoreCase("elementalkits")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("reload")) {
					plugin.reloadConfig();
					plugin.load();
				}
			}
		}

		return true;
	}

}
