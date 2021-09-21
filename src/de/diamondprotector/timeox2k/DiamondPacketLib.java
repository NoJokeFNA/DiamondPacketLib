package de.diamondprotector.timeox2k;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.diamondprotector.timeox2k.events.PlayerJoinQuitEvent;
import de.diamondprotector.timeox2k.nms.PacketInjector;
import de.diamondprotector.timeox2k.utils.VersionFormater;

public class DiamondPacketLib extends JavaPlugin {
	
	
	//Instance of Main-Class
	private static DiamondPacketLib instance;
	//Instance of VersionFormater
	private VersionFormater versionFormater;
	//Instance of PacketInjector
	private PacketInjector packetInjector;

	@Override
	public void onEnable() {
		instance = this;
		versionFormater = new VersionFormater();
		packetInjector = new PacketInjector();
		
		getServer().getPluginManager().registerEvents(new PlayerJoinQuitEvent(), this);
		
		//On reload inject the Listener again
	    Bukkit.getOnlinePlayers()
        .forEach(
            player ->
                getPacketInjector().injectPlayer(player));
	}
	
	@Override
	public void onDisable() {
		//On reload uninject the Listener (in case of a reload (Players are online) they will get reinjected on enable
		 Bukkit.getOnlinePlayers()
	        .forEach(
	            player ->
	                getPacketInjector().uninjectPlayer(player));
	}
	
	public static DiamondPacketLib getInstance() {
		return instance;
	}
	
	public VersionFormater getVersionFormater() {
		return versionFormater;
	}
	
	public PacketInjector getPacketInjector() {
		return packetInjector;
	}
	

}
