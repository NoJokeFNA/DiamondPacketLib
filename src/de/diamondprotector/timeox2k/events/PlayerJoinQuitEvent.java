package de.diamondprotector.timeox2k.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.diamondprotector.timeox2k.DiamondPacketLib;

public class PlayerJoinQuitEvent implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		
		DiamondPacketLib.getInstance().getPacketInjector().injectPlayer(player);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		
		DiamondPacketLib.getInstance().getPacketInjector().uninjectPlayer(player);
	}
	
}
