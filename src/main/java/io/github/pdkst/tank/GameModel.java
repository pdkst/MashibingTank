package io.github.pdkst.tank;

import io.github.pdkst.tank.model.GameObject;
import io.github.pdkst.tank.model.Tank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Facade 门面模式
 *
 * @author pdkst
 * @since 2021/4/12
 */
@Data
@RequiredArgsConstructor
public class GameModel {

    final List<GameObject> gameObjects = new ArrayList<>();
    final Collider<GameObject> collider = new BulletCollider();
    private final TankFrame tankFrame;
    private final Tank myTank;
    private final int width;
    private final int height;

    public GameModel(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
        this.width = tankFrame.getWidth();
        this.height = tankFrame.getHeight();
        final int initTankCount = PropertyManager.getIntProperty("initTankCount");
        // 自己坦克
        this.myTank = new Tank(this, 50, 50);
        myTank.setGroup(Group.GOOD);
        // 敌人坦克
        for (int i = 0; i < initTankCount; i++) {
            final Tank tank = new Tank(this, 100 * i, 300);
            addGameObject(tank);
        }
    }

    public void addGameObject(GameObject object) {
        gameObjects.add(object);
    }

    public void removeGameObject(GameObject object) {
        gameObjects.add(object);
    }

    public void paint(Graphics graphics) {

        if (myTank != null) {
            myTank.paint(graphics);
        }
        for (int i = 0; i < gameObjects.size(); i++) {
            final GameObject object = gameObjects.get(i);
            object.paint(graphics);
            if (!object.isLiving()) {
                gameObjects.remove(object);
                object.die();
            }
        }
        // 碰撞检查
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = 0; j < gameObjects.size(); j++) {
                final GameObject o1 = gameObjects.get(i);
                final GameObject o2 = gameObjects.get(j);
                collider.collide(o1, o2, unused -> {
                    gameObjects.remove(o1);
                    gameObjects.remove(o2);
                    o1.die();
                    o2.die();
                });
            }
        }
    }
}
