package net.swordie.ms.connection.netty;

import net.swordie.ms.client.Client;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import net.swordie.ms.connection.crypto.AESCipher;
import org.apache.log4j.LogManager;
import net.swordie.ms.connection.packet.Login;
import net.swordie.ms.handlers.EventManager;

import java.util.HashMap;
import java.util.Map;

import static net.swordie.ms.connection.netty.NettyClient.CLIENT_KEY;

public class ChannelAcceptor implements Runnable {

    public net.swordie.ms.world.Channel channel;
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    @Override
    public void run() {
        // Taken from http://netty.io/wiki/user-guide-for-4.x.html

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) {

                    ch.pipeline().addLast(new PacketDecoder(), new ChannelHandler(), new PacketEncoder());

                    int siv = (int) (Math.random() * Integer.MAX_VALUE);
                    int riv = (int) (Math.random() * Integer.MAX_VALUE);

                    Client c = new Client(ch, siv, riv);

                    c.write(Login.sendConnect(riv, siv, c.getPort() == 8484));

                    ch.attr(CLIENT_KEY).set(c);
                    ch.attr(Client.AES_CIPHER).set(new AESCipher());

                    EventManager.addFixedRateEvent(c::sendPing, 0, 10000);
                }
            });

            b.childOption(ChannelOption.TCP_NODELAY, true);
            b.childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(channel.getPort()).sync();
            log.info(String.format("Channel %d listening on port %d", channel.getChannelId(), channel.getPort()));
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
