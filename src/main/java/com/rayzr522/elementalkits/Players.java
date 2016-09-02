
package com.rayzr522.elementalkits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.rayzr522.elementalkits.utils.TextUtils;

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

	public static PlayerData get(Player p, String name) {
		return get(p.getUniqueId(), name);
	}

	public static PlayerData get(UUID id, String name) {
		List<PlayerData> homeList = get(id);
		for (PlayerData home : homeList) {
			if (TextUtils.enumFormat(name).equals(TextUtils.enumFormat(home.getName()))) { return home; }
		}
		return null;
	}

	public static void set(Player p, String name) {

		List<PlayerData> homeList = get(p);
		PlayerData home = get(p, name);

		if (home == null) {
			home = new PlayerData(p, name);
		} else {
			homeList.remove(home);
			home.setLocation(p.getLocation());
		}

		homeList.add(home);
		players.put(p.getUniqueId(), homeList);

	}

	public static boolean del(UUID id, String name) {

		PlayerData home = get(id, name);
		if (home == null) { return false; }
		return del(id, home);

	}

	public static boolean del(UUID id, PlayerData home) {

		List<PlayerData> homeList = get(id);

		if (homeList.remove(home)) {
			players.put(id, homeList);
			return true;
		}

		return false;

	}

}
