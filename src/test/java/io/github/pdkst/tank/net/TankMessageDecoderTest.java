package io.github.pdkst.tank.net;

import io.github.pdkst.tank.Dir;
import io.github.pdkst.tank.model.Tank;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pdkst
 * @since 2021/4/27
 */
class TankMessageDecoderTest {

    @Test
    public void testDecoder() {
        final Tank msg = new Tank(1, 2);
        msg.setDir(Dir.UP);
        final ByteBuf out = Unpooled.buffer(9);
        out.writeInt(msg.getX());
        out.writeInt(msg.getY());
        out.writeByte(msg.getDir().ordinal());
        final TankMessageDecoder tankMessageDecoder = new TankMessageDecoder();
        final EmbeddedChannel embeddedChannel = new EmbeddedChannel(tankMessageDecoder);
        embeddedChannel.writeInbound(out);
        final Tank newTank = embeddedChannel.readInbound();
        assertEquals(msg.getX(), newTank.getX());
        assertEquals(msg.getY(), newTank.getY());
        assertEquals(msg.getDir(), newTank.getDir());

    }
}