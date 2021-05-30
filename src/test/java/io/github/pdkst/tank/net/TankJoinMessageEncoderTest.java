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
class TankJoinMessageEncoderTest {

    @Test
    public void testEncoder() {
        final Tank tank = new Tank(1, 2);
        tank.setDir(Dir.UP);
        final TankJoinMessageEncoder tankJoinMessageEncoder = new TankJoinMessageEncoder();
        final EmbeddedChannel embeddedChannel = new EmbeddedChannel(tankJoinMessageEncoder);
        embeddedChannel.writeOutbound(tank);

        final ByteBuf msg = embeddedChannel.readOutbound();
        assertEquals(msg.readInt(), tank.getX());
        assertEquals(msg.readInt(), tank.getY());
        assertEquals(msg.readByte(), tank.getDir().ordinal());
    }
}