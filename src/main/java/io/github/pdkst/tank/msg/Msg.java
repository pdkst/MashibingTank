package io.github.pdkst.tank.msg;

/**
 * @author pdkst
 * @since 2021/5/30
 */
public abstract class Msg {
    /**
     *
     */
    public abstract void handle();

    /**
     * 序列化
     *
     * @return
     */
    public abstract byte[] toBytes();

    /**
     * 反序列化
     *
     * @param bytes
     */
    public abstract void parse(byte[] bytes);

    /**
     * @return
     */
    public abstract MsgType msgType();
}
