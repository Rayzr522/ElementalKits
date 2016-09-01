
package com.rayzr522.elementalkits;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class ElementalKits extends JavaPlugin {

	private Logger logger;

	@Override
	public void onEnable() {

		logger = getLogger();

		logger.info(versionString() + " enabled!");

	}

	public void onDisable() {

		logger.info(versionString() + " disabled!");

	}

	public String versionString() {

		return getName() + " v" + getDescription().getVersion();

	}

}
