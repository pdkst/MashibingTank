package io.github.pdkst.tank;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pdkst
 * @since 2021/4/11
 */
@Slf4j
public class TankFrame extends GameFrame {
    private Tank myTank;
    final List<Tank> tanks = new LinkedList<>();
    final List<Bullet> bullets = new LinkedList<>();
    final List<Explode> explodes = new LinkedList<>();

    public TankFrame() throws HeadlessException {
        super(800, 600);
        setTitle("坦克大战");
        this.myTank = new Tank(this, 50, 50);
        myTank.setGroup(Group.GOOD);
        this.bullets.add(new Bullet(myTank));
    }

    @Override
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
                removeKeyListener(tank.getMyKeyListener());
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
