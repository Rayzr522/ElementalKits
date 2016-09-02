
package com.rayzr522.elementalkits;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.rayzr522.elementalkits.utils.ConfigManager;
import com.rayzr522.elementalkits.utils.Msg;

public class ElementalKits extends JavaPlugin {

	private Logger			logger;
	private ConfigManager	cm;

	@Override
	public void onEnable() {

		logger = getLogger();
		cm = new ConfigManager(this);

		load();

		getCommand("ekit").setExecutor(new CommandListener(this));
		getCommand("elementalkits").setExecutor(new CommandListener(this));

		logger.info(versionString() + " enabled!");

	}

	public void onDisable() {

		logger.info(versionString() + " disabled!");

	}

	public void load() {

		Msg.load(cm.getOrCreate("messages.yml"));
		Players.load(cm.getOrCreate("players.yml"));
		Config.load(this);

	}

	public String versionString() {

		return getName() + " v" + getDescription().getVersion();

	}

}
