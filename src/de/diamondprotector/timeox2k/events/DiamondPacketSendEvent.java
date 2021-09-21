package de.diamondprotector.timeox2k.events;

import io.netty.channel.Channel;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DiamondPacketSendEvent extends Event implements Cancellable {

  private final Player player;
  private final Object packet;
  private final Channel channel;
  private boolean isCancelled;

  public DiamondPacketSendEvent(Player player, Object packet, Channel channel) {
    this.player = player;
    this.packet = packet;
    this.channel = channel;
  }

  private static final HandlerList HANDLERS = new HandlerList();

  @Override
  public HandlerList getHandlers() {
    return HANDLERS;
  }

  public static HandlerList getHandlerList() {
    return HANDLERS;
  }

  public Player getPlayer() {
    return player;
  }

  public Channel getChannel() {
    return channel;
  }

  public Object getPacket() {
    return packet;
  }

  @Override
  public boolean isCancelled() {
    return isCancelled;
  }

  @Override
  public void setCancelled(boolean cancelled) {
    this.isCancelled = cancelled;
  }
}
