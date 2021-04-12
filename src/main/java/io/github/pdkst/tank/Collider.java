package io.github.pdkst.tank;

import io.github.pdkst.tank.model.GameBlock;

import java.util.function.Consumer;

/**
 * 碰撞检测
 *
 * @author pdkst
 * @since 2021/4/12
 */
public interface Collider<T extends GameBlock> {
    /**
     * 碰撞
     *
     * @param o1 游戏对象1
     * @param o2 游戏对象2
     * @param consumer 碰撞时方法
     */
    void collide(T o1, T o2, Consumer<Void> consumer);
}
