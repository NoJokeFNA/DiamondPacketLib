package de.diamondprotector.timeox2k.nms;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.diamondprotector.timeox2k.DiamondPacketLib;
import de.diamondprotector.timeox2k.nms.reader.LegacyPacketReader;
import de.diamondprotector.timeox2k.nms.reader.ModernPacketReader;

public class PacketInjector {

	private LegacyPacketReader legacyPacketReader;
	private ModernPacketReader modernPacketReader;

	public void injectPlayer(final Player player) {
		String formatedVersion = DiamondPacketLib.getInstance().getVersionFormater().getVersionString(
				Bukkit.getServer().getClass().getPackage().getName().replace("org.bukkit.craftbukkit.", ""));

		if (formatedVersion.equalsIgnoreCase("modern")) {
			this.modernPacketReader = new ModernPacketReader();
			modernPacketReader.injectPlayer(player);
		} else {
			this.legacyPacketReader = new LegacyPacketReader();
			legacyPacketReader.injectPlayer(player);
		}
	}

	public void uninjectPlayer(final Player player) {
		String formatedVersion = DiamondPacketLib.getInstance().getVersionFormater().getVersionString(
				Bukkit.getServer().getClass().getPackage().getName().replace("org.bukkit.craftbukkit.", ""));

		if (formatedVersion.equalsIgnoreCase("modern")) {
			this.modernPacketReader = new ModernPacketReader();
			modernPacketReader.removePlayer(player);
		} else {
			this.legacyPacketReader = new LegacyPacketReader();
			legacyPacketReader.removePlayer(player);
		}
	}
}
