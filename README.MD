# DiamondPacketLib
## _Advanced Spigot Packet Library_

DiamondPacketLib is a Spigot Packet Library optimzied for 1.8 - latest Minecraft Versions that made working with Packets as easy as using CraftBukkit Events

## Features

- Simple Events
- Support for 1.8 - 1.17+ included

## How to use

You may know the CraftBukkit PlayerJoinEvent, it is just as easy to use the DiamondPacketReceiveEvent or the DiamondPacketSendEvent event, the first event is called as soon as the player sends a packet to the server and the other event as soon as the server sends a packet to the player. (Both can be canceled). The events pass you an object called Packet which is the packet the event catches through the ChannelDuplexHandler, additionally it passes you directly the player who sent it or should be sent to, just like you can cancel everything.

## Example

	@EventHandler
	public void onReceive(DiamondPacketReceiveEvent event) {
		final Player player = event.getPlayer();
		
		if(event.getPacket().getClass().getSimpleName().equalsIgnoreCase("PacketPlayInEnchantItem")) {
			event.setCancelled(true);
			player.sendMessage("§cEnchanting Items is not cool!");
		}
	}