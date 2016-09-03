package com.rayzr522.elementalkits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MainListener implements Listener {
	
	ElementalKits plugin;
	
	public MainListener(ElementalKits instance) {
		this.plugin = instance;
	}
	
	@EventHandler
	public void onPlayerAttack(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			Player p1 = (Player) e.getDamager();
			
			if(Players.get(p) == Players.get(p1)) {
				e.setCancelled(true);
			}
		}
	}
}
