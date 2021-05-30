package io.github.pdkst.tank.net;

import io.github.pdkst.tank.model.Tank;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.function.Consumer;

/**
 * @author pdkst
 * @since 2021/4/22
 */
@SuppressWarnings("DuplicatedCode")
public class NettyTankClient implements Runnable {
    private SocketChannel socketChannel;
    final Bootstrap b = new Bootstrap();
    EventLoopGroup group = new NioEventLoopGroup();
    private final String host;
    private final Consumer<Tank> consumer;

    public NettyTankClient(String host, Consumer<Tank> consumer) {
        this.host = host;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        try {
            final ChannelFuture future = b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            socketChannel = ch;
                            ch.pipeline()
                                    .addLast(new TankJoinMessageEncoder())
                                    .addLast(new TankJoinMessageDecoder())
                                    .addLast(new ClientHandler(consumer));
                        }
                    })
                    .connect(host, 10000)
                    .syncUninterruptibly();

            future.channel().closeFuture().syncUninterruptibly();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void send(Tank msg) {
        socketChannel.writeAndFlush(msg);
    }

    public void closeConnection() {
        socketChannel.close();
    }
}
