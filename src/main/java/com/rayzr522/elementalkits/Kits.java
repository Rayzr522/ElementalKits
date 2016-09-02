
package com.rayzr522.elementalkits;

import java.util.HashMap;

import com.rayzr522.elementalkits.kits.Kit;

public class Kits {

	private ElementalKits			plugin;
	private HashMap<Integer, Kit>	kits	= new HashMap<>();

	public Kits(ElementalKits plugin) {
		this.plugin = plugin;
	}

	public void addKit(Kit kit) {
		kits.put(kits.size(), kit);
	}

}
