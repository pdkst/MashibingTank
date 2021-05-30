package io.github.pdkst.tank.msg;

import io.github.pdkst.tank.Dir;

import java.io.*;
import java.util.UUID;

/**
 * @author pdkst
 * @since 2021/5/30
 */
public class TankMovingStartMessage extends Msg {
    private UUID id;
    private boolean moving;
    private Dir dir;
    private int x;
    private int y;

    public TankMovingStartMessage() {
        super(MsgType.TankMovingStart);
    }

    @Override
    public void handle() {

    }

    @Override
    public byte[] toBytes() {
        try (final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             final ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);) {
            out.writeLong(id.getMostSignificantBits());
            out.writeLong(id.getLeastSignificantBits());
            out.writeBoolean(moving);
            out.writeByte(dir.ordinal());
            out.writeInt(x);
            out.writeInt(y);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    @Override
    public void parse(byte[] bytes) {
        try (final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             final ObjectInputStream in = new ObjectInputStream(byteArrayInputStream)) {
            id = new UUID(in.readLong(), in.readLong());
            moving = in.readBoolean();
            dir = Dir.values()[in.readByte()];
            x = in.readInt();
            y = in.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
