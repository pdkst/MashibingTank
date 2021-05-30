package io.github.pdkst.tank.net;

import io.github.pdkst.tank.msg.Msg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author pdkst
 * @since 2021/4/26
 */
public class MessageEncoder extends MessageToByteEncoder<Msg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.toBytes());
    }
}
