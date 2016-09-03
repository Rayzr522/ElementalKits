
package com.rayzr522.elementalkits.utils;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.google.common.base.Strings;

public class Msg {

	public static final String				SEPARATOR	= ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + Strings.repeat("-", 53);

	private static HashMap<String, String>	messages	= new HashMap<String, String>();

	/**
	 * Loads all messages from the given config, replacing paths inside [[]]
	 * with the given message for that path
	 * 
	 * @param config
	 */
	public static void load(YamlConfiguration config) {

		messages.clear();

		for (String key : config.getKeys(true)) {

			if (config.get(key) instanceof String) {
				messages.put(key, config.getString(key));
			}

		}

		Pattern regex = Pattern.compile("\\[\\[[a-zA-Z-_.]{1,}\\]\\]");

		for (Entry<String, String> entry : messages.entrySet()) {

			String msg = entry.getValue();
			Matcher matcher = regex.matcher(msg);

			while (matcher.find()) {

				String inputKey = matcher.group();
				inputKey = inputKey.substring(2, inputKey.length() - 2);

				if (messages.containsKey(inputKey)) {

					msg = msg.replaceFirst(regex.pattern(), messages.get(inputKey));

				}

			}

		}

	}

	/**
	 * Sends the player the message with the given key and substitute all keys
	 * with the given parameters
	 * 
	 * @param sender
	 */
	public static void send(Player p, String key, String... strings) {

		String msg = get(key);

		for (int i = 0; i < strings.length; i++) {
			msg = msg.replace("{" + i + "}", TextUtils.colorize(strings[i]));
		}

		p.sendMessage(msg);

	}

	/**
	 * Sends the player the message with the given key and substitute all keys
	 * with the given parameters
	 * 
	 * @param sender
	 */
	public static void send(CommandSender sender, String key, String... strings) {

		String msg = get(key);

		for (int i = 0; i < strings.length; i++) {
			msg = msg.replace("{" + i + "}", TextUtils.colorize(strings[i]));
		}

		sender.sendMessage(msg);

	}

	/**
	 * Sends the player a horizontal seperator line
	 * 
	 * @param sender
	 */
	public static void sep(Player p) {
		p.sendMessage(SEPARATOR);
	}

	/**
	 * Sends the player a horizontal seperator line
	 * 
	 * @param sender
	 */
	public static void sep(CommandSender sender) {
		sender.sendMessage(SEPARATOR);
	}

	/**
	 * Gets the message with the given key
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {

		return messages.containsKey(key) ? TextUtils.colorize(messages.get(key)) : key;

	}

}
