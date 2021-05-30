package io.github.pdkst.tank.net;

import io.github.pdkst.tank.msg.Msg;
import io.github.pdkst.tank.msg.MsgType;
import io.github.pdkst.tank.msg.TankJoinMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author pdkst
 * @since 2021/4/26
 */
public class MessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        in.markReaderIndex();
        final MsgType msgType = MsgType.values()[in.readByte()];
        final int length = in.readInt();
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }
        final byte[] bytes = new byte[length];
        in.readBytes(bytes, 5, length);
        Msg msg = null;
        switch (msgType) {
            case TankJoin:
                msg = new TankJoinMessage();
            default:
                msg = new TankJoinMessage();
        }
        msg.parse(bytes);
        out.add(msg);
    }
}
