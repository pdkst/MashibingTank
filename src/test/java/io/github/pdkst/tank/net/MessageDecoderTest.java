package io.github.pdkst.tank.net;

import io.github.pdkst.tank.Dir;
import io.github.pdkst.tank.Group;
import io.github.pdkst.tank.msg.TankJoinMessage;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pdkst
 * @since 2021/4/27
 */
class MessageDecoderTest {

    @Test
    public void testDecoder() {
        final TankJoinMessage tankJoinMessage = new TankJoinMessage(1, 2, Dir.UP, true, false, Group.GOOD, UUID.randomUUID());
        final MessageDecoder messageDecoder = new MessageDecoder();
        final EmbeddedChannel embeddedChannel = new EmbeddedChannel(messageDecoder);
        embeddedChannel.writeInbound(tankJoinMessage);
        final TankJoinMessage newTank = embeddedChannel.readInbound();
        assertEquals(tankJoinMessage, newTank);
    }
}