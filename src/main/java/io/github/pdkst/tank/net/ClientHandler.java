package io.github.pdkst.tank.net;

import io.github.pdkst.tank.model.Tank;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.function.Consumer;

/**
 * @author pdkst
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private final Consumer<Tank> consumer;
    private Channel channel;

    public ClientHandler(Consumer<Tank> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.channel = ctx.channel();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            Tank tank = (Tank) msg;
            if (consumer != null) {
                consumer.accept(tank);
            }

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    public void send(Tank tank) {
        channel.writeAndFlush(tank);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}