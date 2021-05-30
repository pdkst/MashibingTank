package io.github.pdkst.tank.net;

import io.github.pdkst.tank.Dir;
import io.github.pdkst.tank.model.Tank;
import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pdkst
 * @since 2021/4/27
 */
class MessageEncoderTest {

    @Test
    public void testEncoder() {
        final Tank tank = new Tank(1, 2);
        tank.setDir(Dir.UP);
        final MessageEncoder messageEncoder = new MessageEncoder();
        final EmbeddedChannel embeddedChannel = new EmbeddedChannel(messageEncoder);
        embeddedChannel.writeOutbound(tank);

        final ByteBuf msg = embeddedChannel.readOutbound();
        assertEquals(msg.readInt(), tank.getX());
        assertEquals(msg.readInt(), tank.getY());
        assertEquals(msg.readByte(), tank.getDir().ordinal());
    }
}