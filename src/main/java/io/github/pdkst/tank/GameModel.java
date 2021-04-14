package io.github.pdkst.tank;

import io.github.pdkst.tank.cor.ColliderChain;
import io.github.pdkst.tank.model.GameBlock;
import io.github.pdkst.tank.model.GameObject;
import io.github.pdkst.tank.model.Tank;
import io.github.pdkst.tank.model.Wall;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
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

    final List<GameBlock> gameObjects = new ArrayList<>();
    final ColliderChain colliderChain = new ColliderChain();
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
            addBlock(tank);
        }
        // 墙
        addBlock(new Wall(150, 150, 200, 50));
        addBlock(new Wall(550, 150, 200, 50));
        addBlock(new Wall(300, 300, 50, 200));
        addBlock(new Wall(550, 300, 50, 200));
    }

    public void addBlock(GameBlock object) {
        gameObjects.add(object);
    }

    public void removeBlock(GameBlock object) {
        gameObjects.remove(object);
    }

    public void paint(Graphics graphics) {

        if (myTank != null) {
            myTank.paint(graphics);
        }
        for (int i = 0; i < gameObjects.size(); i++) {
            final GameBlock block = gameObjects.get(i);
            block.paint(graphics);
            if (block instanceof GameObject) {
                final GameObject object = (GameObject) block;
                if (!object.isLiving()) {
                    gameObjects.remove(object);
                    object.die();
                }
            }
        }
        // 碰撞检查
        for (int i = 0; i < gameObjects.size() - 1; i++) {
            final GameBlock o1 = gameObjects.get(i);
            for (int j = i + 1; j < gameObjects.size(); j++) {
                final GameBlock o2 = gameObjects.get(j);
                colliderChain.collide(o1, o2);
            }
        }
    }
}
