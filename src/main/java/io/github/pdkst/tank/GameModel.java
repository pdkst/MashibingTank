package io.github.pdkst.tank;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pdkst
 * @since 2021/4/12
 */
@Data
@RequiredArgsConstructor
public class GameModel {

    private final TankFrame tankFrame;
    private final Tank myTank;
    private final int width;
    private final int height;

    final List<Tank> tanks = new LinkedList<>();
    final List<Bullet> bullets = new LinkedList<>();
    final List<Explode> explodes = new LinkedList<>();

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
            addTank(tank);
        }
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void addTank(Tank tank) {
        tanks.add(tank);
    }

    public void addExplode(Explode explode) {
        explodes.add(explode);
    }

    public void paint(Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawString("子弹数量：" + bullets.size(), 30, 60);
        graphics.drawString("坦克数量：" + tanks.size(), 30, 90);
        graphics.setColor(color);

        if (myTank != null) {
            myTank.paint(graphics);
        }
        final Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            final Bullet bullet = iterator.next();
            bullet.paint(graphics);
            if (!bullet.isLiving()) {
                iterator.remove();
            }
        }
        final Iterator<Tank> tankIterator = tanks.iterator();
        while (tankIterator.hasNext()) {
            Tank tank = tankIterator.next();
            if (tank.isLiving()) {
                tank.paint(graphics);
            } else {
                tankIterator.remove();
                tankFrame.removeKeyListener(tank.getMyKeyListener());
            }
        }

        // 碰撞检查
        for (final Bullet bullet : bullets) {
            for (final Tank tank : tanks) {
                bullet.collideWith(tank);
            }
        }
        final Iterator<Explode> explodeIterator = explodes.iterator();
        while (explodeIterator.hasNext()) {
            Explode explode = explodeIterator.next();
            if (explode.isLiving()) {
                explode.paint(graphics);
            } else {
                explodeIterator.remove();
            }
        }

    }
}
