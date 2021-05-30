package io.github.pdkst.tank.msg;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author pdkst
 * @since 2021/5/30
 */
@Getter
@AllArgsConstructor
public enum MsgType {
    /**
     * 加入坦克
     */
    TankJoin,
    TankDirChange,
    TankStop,
    TankMovingStart,
    /** 加入子弹 */
    BulletJoin,
    ;
}
