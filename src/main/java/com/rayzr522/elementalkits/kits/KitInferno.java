
package com.rayzr522.elementalkits.kits;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.rayzr522.elementalkits.ElementalKits;
import com.rayzr522.elementalkits.Players;
import com.rayzr522.elementalkits.utils.item.ItemUtils;

public class KitInferno extends Kit {

	public static int				ID			= -1;

	private HashMap<UUID, Long>		cooldowns	= new HashMap<>();
	private static final ItemStack	bone		= ItemUtils.makeItem("bone, named &6Fire &cWand");
	public static final long		cooldown	= 6000;

	public KitInferno(ElementalKits plugin) {
		super(plugin);
	}

	@Override
	public void init() {
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(bone);
	}

	private long lastTime(Player p) {
		return cooldowns.containsKey(p.getUniqueId()) ? cooldowns.get(p.getUniqueId()) : -1;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {

		if (!e.getAction().toString().startsWith("LEFT_CLICK") || Players.get(e.getPlayer()) != ID) { return; }

		Player p = e.getPlayer();
		if (!p.getInventory().getItemInMainHand().equals(bone)) { return; }

		long now = System.currentTimeMillis();
		long last = lastTime(p) == -1 ? now - cooldown : lastTime(p);

		if (now - last < cooldown) {
			p.playSound(p.getLocation(), Sound.BLOCK_CLOTH_BREAK, 1.0f, 1.0f);
			return;
		}

		Vector loc = p.getTargetBlock((Set<Material>) null, 1).getType() == Material.AIR ? p.getLocation().getDirection().multiply(2.0) : p.getLocation().getDirection();

		cooldowns.put(p.getUniqueId(), now);
		p.getWorld().spawnEntity(p.getEyeLocation().add(loc), EntityType.FIREBALL).setVelocity(p.getLocation().getDirection().multiply(2.5));

	}

	@EventHandler
	public void onPlayerSpawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if (Players.get(p) == ID && !(p.getInventory().contains(bone))) {
			p.getInventory().addItem(bone);
		}
	}

	@EventHandler
	public void onInvInteract(InventoryClickEvent e) {
		if (e.getInventory().getType() == InventoryType.CRAFTING || e.getInventory().getType() == InventoryType.PLAYER) {
			return;
		} else {
			if (e.getCurrentItem().equals(bone)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent e) {
		ItemStack item = e.getItemDrop().getItemStack();

		if (item.equals(bone)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDeathEvent(PlayerDeathEvent event) {
		Iterator<ItemStack> iterator = event.getDrops().iterator();

		if (iterator.hasNext()) {
			for (ItemStack item = iterator.next(); iterator.hasNext(); item = iterator.next()) {
				if (item.equals(bone)) {
					event.getDrops().remove(bone);
				}
			}
		}

	}

	@EventHandler
	public void onPreCraft(CraftItemEvent e) {
		if (e.getInventory().contains(bone)) {
			e.setCancelled(true);
		}
	}

	@Override
	public void onKitRemove(Player p) {
		System.out.println("Removing bone!");
		p.getInventory().remove(bone);
	}

}
