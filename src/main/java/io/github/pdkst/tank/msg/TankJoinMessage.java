package io.github.pdkst.tank.msg;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import io.github.pdkst.tank.Dir;
import io.github.pdkst.tank.GameModel;
import io.github.pdkst.tank.Group;
import io.github.pdkst.tank.model.Tank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;

/**
 * @author pdkst
 * @since 2021/4/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TankJoinMessage extends Msg {
    private int x;
    private int y;
    private Dir dir;
    private boolean isMoving;
    private boolean isLiving;
    private Group group;
    private UUID id;

    public TankJoinMessage() {
        super(MsgType.TankJoin);
    }

    public TankJoinMessage(Tank tank) {
        this();
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
        this.isMoving = tank.isMoving();
        this.isLiving = tank.isLiving();
        this.id = tank.getId();
    }

    public TankJoinMessage(int x, int y, Dir dir, boolean isMoving, boolean isLiving, Group group, UUID id) {
        this();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.isMoving = isMoving;
        this.isLiving = isLiving;
        this.group = group;
        this.id = id;
    }

    @Override
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

    @Override
    public void parse(byte[] bytes) {
        try (final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             final ObjectInputStream in = new ObjectInputStream(byteArrayInputStream)) {
            x = in.readInt();
            y = in.readInt();
            dir = Dir.values()[in.readByte()];
            isMoving = in.readBoolean();
            isLiving = in.readBoolean();
            group = Group.values()[in.readByte()];
            id = new UUID(in.readLong(), in.readLong());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handle() {
        final Tank myTank = GameModel.getInstance().getMyTank();
        final UUID id = myTank.getId();
        if (id.equals(this.id)) {
            return;
        }
        GameModel.getInstance().addBlock(toTank());
    }

    private Tank toTank() {
        final Tank tank = new Tank(x, y);
        tank.setId(id);
        tank.setDir(dir);
        tank.setLiving(isLiving);
        tank.setMoving(isMoving);
        tank.setGroup(Group.BAD);
        tank.setDir(dir);
        return tank;
    }

    @Override
    public MsgType msgType() {
        return MsgType.TankJoin;
    }
}
