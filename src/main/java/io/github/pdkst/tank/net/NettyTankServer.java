package io.github.pdkst.tank.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author pdkst
 * @since 2021/4/26
 */
@SuppressWarnings("DuplicatedCode")
public class NettyTankServer {
    final ServerBootstrap bootstrap = new ServerBootstrap();

    public static void main(String[] args) throws InterruptedException {
        new NettyTankServer().connect();
    }

    public void connect() throws InterruptedException {
        final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        final EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        try {
            final ChannelFuture future = bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            System.out.println(socketChannel);
                            final ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline
                                    .addLast(new TankJoinMessageEncoder())
                                    .addLast(new TankJoinMessageDecoder())
                                    .addLast(new ServerHandler());
                        }
                    })
                    .bind(10000)
                    .sync();
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}