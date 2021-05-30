package io.github.pdkst.tank.msg;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author pdkst
 * @since 2021/5/30
 */
@NoArgsConstructor
@AllArgsConstructor
public abstract class Msg {
    private MsgType msgType;

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
    public MsgType msgType() {
        return msgType;
    }
}
