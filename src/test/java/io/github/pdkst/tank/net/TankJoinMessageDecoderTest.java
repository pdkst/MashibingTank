package io.github.pdkst.tank.net;

import io.github.pdkst.tank.Dir;
import io.github.pdkst.tank.Group;
import io.github.pdkst.tank.model.Tank;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pdkst
 * @since 2021/4/27
 */
class TankJoinMessageDecoderTest {

    @Test
    public void testDecoder() {
        final TankJoinMessage tankJoinMessage = new TankJoinMessage(1, 2, Dir.UP, true, false, Group.GOOD, UUID.randomUUID());
        final TankJoinMessageDecoder tankJoinMessageDecoder = new TankJoinMessageDecoder();
        final EmbeddedChannel embeddedChannel = new EmbeddedChannel(tankJoinMessageDecoder);
        embeddedChannel.writeInbound(tankJoinMessage);
        final TankJoinMessage newTank = embeddedChannel.readInbound();
        assertEquals(tankJoinMessage, newTank);
    }
}