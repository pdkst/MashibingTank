package io.github.pdkst.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author pdkst
 * @since 2021/4/26
 */
public class TankJoinMessageEncoder extends MessageToByteEncoder<TankJoinMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, TankJoinMessage msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.toBytes());
    }
}
