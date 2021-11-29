package de.diamondprotector.timeox2k.nms.reader;

import de.diamondprotector.timeox2k.DiamondPacketLib;
import de.diamondprotector.timeox2k.events.DiamondPacketReceiveEvent;
import de.diamondprotector.timeox2k.events.DiamondPacketSendEvent;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LegacyPacketReader {
  public void removePlayer(final Player player) {
    try {
      final Object handle = player.getClass().getMethod("getHandle").invoke(player);
      final Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
      final Object networkManager = playerConnection.getClass().getField("networkManager").get(playerConnection);
      final Channel channel = (Channel) networkManager.getClass().getField("channel").get(networkManager);

      channel.eventLoop().submit(() -> {
        channel.pipeline().remove("DiamondPacketLib" + player.getName());
        return null;
      });
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public void injectPlayer(final Player player) {
    try {
      final Object handle = player.getClass().getMethod("getHandle").invoke(player);
      final Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
      final Object networkManager = playerConnection.getClass().getField("networkManager").get(playerConnection);
      final Channel channel = (Channel) networkManager.getClass().getField("channel").get(networkManager);

      final ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
        @Override
        public void channelRead(final ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
          try {
            DiamondPacketReceiveEvent diamondPacketReceiveEvent = new DiamondPacketReceiveEvent(player, packet, channel);
            Bukkit.getScheduler().runTask(DiamondPacketLib.getInstance(), () -> Bukkit.getPluginManager().callEvent(diamondPacketReceiveEvent));
            if (diamondPacketReceiveEvent.isCancelled()) {
              return;
            }
          } catch (Exception exception) {
            exception.printStackTrace();
          }

          super.channelRead(channelHandlerContext, packet);
        }

        @Override
        public void write(final ChannelHandlerContext channelHandlerContext, final Object packet, final ChannelPromise channelPromise) throws Exception {
          try {
            DiamondPacketSendEvent diamondPacketSendEvent = new DiamondPacketSendEvent(player, packet, channel);

            // Has to be made with a scheduler as Spigot throws Issues without
            Bukkit.getScheduler().runTask(DiamondPacketLib.getInstance(), () -> Bukkit.getPluginManager().callEvent(diamondPacketSendEvent));
            if (diamondPacketSendEvent.isCancelled()) {
              return;
            }
          } catch (Exception exception) {
            exception.printStackTrace();
          }

          super.write(channelHandlerContext, packet, channelPromise);
        }
      };

      final ChannelPipeline channelPipeline = channel.pipeline();
      channelPipeline.addBefore("packet_handler", "DiamondPacketLib" + player.getName(), channelDuplexHandler);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}