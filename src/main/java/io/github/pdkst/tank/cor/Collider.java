package io.github.pdkst.tank.cor;

import io.github.pdkst.tank.model.GameObject;

/**
 * 碰撞检测
 *
 * @author pdkst
 * @since 2021/4/12
 */
public interface Collider {
    /**
     * 碰撞
     *
     * @param o1 游戏对象1
     * @param o2 游戏对象2
     */
    boolean collide(GameObject o1, GameObject o2);
}
