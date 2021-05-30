package io.github.pdkst.tank.net;

import io.github.pdkst.tank.Dir;
import io.github.pdkst.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

/**
 * @author pdkst
 * @since 2021/4/26
 */
public class TankJoinMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        final TankJoinMessage tankJoinMessage = new TankJoinMessage(
                in.readInt(),
                in.readInt(),
                Dir.values()[in.readByte()],
                in.readBoolean(),
                in.readBoolean(),
                Group.values()[in.readByte()],
                new UUID(in.readLong(), in.readLong())
        );
        out.add(tankJoinMessage);
    }
}
