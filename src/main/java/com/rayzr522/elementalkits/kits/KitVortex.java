
package com.rayzr522.elementalkits.kits;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.rayzr522.elementalkits.ElementalKits;
import com.rayzr522.elementalkits.Players;

public class KitVortex extends Kit {

	public static int	ID		= -1;

	private Random		random	= new Random();

	public KitVortex(ElementalKits plugin) {
		super(plugin);
	}

	@Override
	public void init() {
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList();
	}

	@EventHandler
	public void onEntityDamaged(EntityDamageByEntityEvent e) {

		if (e.getDamager().getType() != EntityType.PLAYER || e.getEntityType() != EntityType.PLAYER) { return; }

		Player p = (Player) e.getDamager();

		if (Players.get(p) != ID) { return; }

		Player target = (Player) e.getEntity();
		
		if (random.nextGaussian() <= 0.33) {
			
			target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
			
		}


	}

	@Override
	public void onKitRemove(Player p) {
	}

}
