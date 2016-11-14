
package com.rayzr522.elementalkits;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Players {

    private static HashMap<UUID, Integer> players = new HashMap<>();

    public static void load(YamlConfiguration config) {

        players.clear();

        for (String key : config.getKeys(false)) {

            UUID id = UUID.fromString(key);

            players.put(id, config.getInt(key));

        }

    }

    public static YamlConfiguration save() {

        YamlConfiguration config = new YamlConfiguration();

        for (Entry<UUID, Integer> entry : players.entrySet()) {

            config.set(entry.getKey().toString(), entry.getValue());

        }

        return config;

    }

    public static int get(Player p) {
        return get(p.getUniqueId());
    }

    public static int get(UUID id) {

        if (!players.containsKey(id)) {
            init(id);
        }
        return players.get(id);

    }

    public static void init(UUID id) {
        players.put(id, 0);
    }

    public static void set(Player p, int id) {
        set(p.getUniqueId(), id);
    }

    public static void set(UUID uid, int id) {
        players.put(uid, id);
    }

}
