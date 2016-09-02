
package com.rayzr522.elementalkits;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Players {

	private static HashMap<UUID, PlayerData> players = new HashMap<>();

	public static void load(YamlConfiguration config) {

		players.clear();

		for (String key : config.getKeys(false)) {

			UUID id = UUID.fromString(key);

			ConfigurationSection playerSection = config.getConfigurationSection(key);

			PlayerData playerData = new PlayerData(id, playerSection);

			players.put(id, playerData);

		}

	}

	public static YamlConfiguration save() {

		YamlConfiguration config = new YamlConfiguration();

		for (Entry<UUID, PlayerData> entry : players.entrySet()) {

			ConfigurationSection playerSection = config.createSection(entry.getKey().toString());

			entry.getValue().save(playerSection);

		}

		return config;

	}

	public static PlayerData get(Player p) {
		return get(p.getUniqueId());
	}

	public static PlayerData get(UUID id) {

		if (!players.containsKey(id)) {
			init(id);
		}
		return players.get(id);

	}

	public static void init(UUID id) {
		players.put(id, new PlayerData(id, null));
	}

	public static PlayerData get(String name) {
		for (Entry<UUID, PlayerData> entry : players.entrySet()) {
			if (entry.getValue().getName().equalsIgnoreCase(name)) { return entry.getValue(); }
		}
		return null;
	}

}
