
package com.rayzr522.elementalkits;

import java.util.HashMap;

import com.rayzr522.elementalkits.kits.Kit;

public class Kits {

	private static HashMap<Integer, Kit> kits = new HashMap<>();

	public static int addKit(Kit kit) {
		int id = kits.size() + 1;
		kits.put(id, kit);
		return id;
	}

	public static Kit getKit(int id) {
		return kits.get(id);
	}

}
