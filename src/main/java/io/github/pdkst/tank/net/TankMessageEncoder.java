package io.github.pdkst.tank.net;

import io.github.pdkst.tank.model.Tank;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author pdkst
 * @since 2021/4/26
 */
public class TankMessageEncoder extends MessageToByteEncoder<Tank> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Tank msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getX());
        out.writeInt(msg.getY());
        out.writeByte(msg.getDir().ordinal());
        ctx.channel().writeAndFlush(out);
    }
}
