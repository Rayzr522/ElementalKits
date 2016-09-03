
package com.rayzr522.elementalkits;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.rayzr522.elementalkits.kits.KitFrost;
import com.rayzr522.elementalkits.kits.KitInferno;
import com.rayzr522.elementalkits.kits.KitVortex;
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

		KitInferno.ID = Kits.addKit(new KitInferno(this));
		KitVortex.ID = Kits.addKit(new KitVortex(this));
		KitFrost.ID = Kits.addKit(new KitFrost(this));

		Bukkit.getPluginManager().registerEvents(new MainListener(this), this);

		CommandListener listener = new CommandListener(this);
		getCommand("ekit").setExecutor(listener);
		getCommand("elementalkits").setExecutor(listener);

		logger.info(versionString() + " enabled!");

	}

	public void onDisable() {

		save();

		logger.info(versionString() + " disabled!");

	}

	public void load() {

		Msg.load(cm.getOrCreate("messages.yml"));
		Players.load(cm.getOrCreate("players.yml"));
		Config.load(this);

	}

	public void save() {

		cm.saveConfig("players.yml", Players.save());

	}

	public String versionString() {

		return getName() + " v" + getDescription().getVersion();

	}

}
