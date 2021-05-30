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

    public static final int HEADER_LENGTH = 5;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        final int readableBytes = in.readableBytes();
        if (readableBytes < HEADER_LENGTH) {
            return;
        }
        in.markReaderIndex();
        final MsgType msgType = MsgType.values()[in.readByte()];
        final int length = in.readInt();
        if (readableBytes < length) {
            in.resetReaderIndex();
            return;
        }
        final byte[] bytes = new byte[length];
        in.readBytes(bytes);
        Msg msg = null;
        switch (msgType) {
            case TankJoin:
                msg = new TankJoinMessage();
                break;
            case TankDirChange:
                break;
            case TankStop:
                break;
            case TankMovingStart:
                break;
            case BulletJoin:
                break;
            default:
                msg = new TankJoinMessage();
                break;
        }
        msg.parse(bytes);
        out.add(msg);
    }
}
