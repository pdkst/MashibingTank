package io.github.pdkst.tank.net;

import io.github.pdkst.tank.Dir;
import io.github.pdkst.tank.model.Tank;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author pdkst
 * @since 2021/4/26
 */
public class TankMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        final Tank tank = new Tank(in.readInt(), in.readInt());
        final Dir dir = Dir.values()[in.readByte()];
        tank.setDir(dir);
        out.add(tank);
    }
}
