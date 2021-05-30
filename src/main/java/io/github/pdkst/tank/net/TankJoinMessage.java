package io.github.pdkst.tank.net;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import io.github.pdkst.tank.Dir;
import io.github.pdkst.tank.Group;
import io.github.pdkst.tank.model.Tank;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.ObjectOutputStream;
import java.util.UUID;

/**
 * @author pdkst
 * @since 2021/4/27
 */
@Data
public class TankJoinMessage {
    private int x;
    private int y;
    private Dir dir;
    private boolean isMoving;
    private boolean isLiving;
    private Group group;
    private UUID id;

    public TankJoinMessage() {
    }

    public TankJoinMessage(Tank tank) {
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
        this.isMoving = tank.isMoving();
        this.isLiving = tank.isLiving();
        this.id = tank.getId();
    }

    public TankJoinMessage(int x, int y, Dir dir, boolean isMoving, boolean isLiving, Group group, UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.isMoving = isMoving;
        this.isLiving = isLiving;
        this.group = group;
        this.id = id;
    }

    @SneakyThrows
    public byte[] toBytes() {
        try (final ByteOutputStream byteOutputStream = new ByteOutputStream();
             final ObjectOutputStream out = new ObjectOutputStream(byteOutputStream);) {
            out.writeInt(x);
            out.writeInt(y);
            out.writeByte(dir.ordinal());
            out.writeBoolean(isMoving);
            out.writeBoolean(isLiving);
            out.writeByte(group.ordinal());
            out.writeLong(id.getMostSignificantBits());
            out.writeLong(id.getLeastSignificantBits());
            return byteOutputStream.getBytes();
        }
    }
}