package de.diamondprotector.timeox2k;

import de.diamondprotector.timeox2k.events.PlayerJoinQuitEvent;
import de.diamondprotector.timeox2k.nms.PacketInjector;
import de.diamondprotector.timeox2k.utils.VersionFormatter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DiamondPacketLib extends JavaPlugin {
  // Instance of Main-Class
  private static DiamondPacketLib instance;
  // Instance of VersionFormatter
  private VersionFormatter versionFormatter;
  // Instance of PacketInjector
  private PacketInjector packetInjector;

  @Override
  public void onEnable() {
    instance = this;
    versionFormatter = new VersionFormatter();
    packetInjector = new PacketInjector();

    getServer().getPluginManager().registerEvents(new PlayerJoinQuitEvent(), this);

    // On reload inject the Listener again
    Bukkit.getOnlinePlayers().forEach(player -> getPacketInjector().injectPlayer(player));
  }

  @Override
  public void onDisable() {
    // On reload uninject the Listener (in case of a reload (Players are online)
    // they will get re-injected on enable)
    Bukkit.getOnlinePlayers().forEach(player -> getPacketInjector().uninjectPlayer(player));
  }

  public static DiamondPacketLib getInstance() {
    return instance;
  }

  public VersionFormatter getVersionFormatter() {
    return versionFormatter;
  }

  public PacketInjector getPacketInjector() {
    return packetInjector;
  }
}