package io.github.pdkst.tank.cor;

import io.github.pdkst.tank.model.Bullet;
import io.github.pdkst.tank.model.GameObject;
import io.github.pdkst.tank.model.Tank;

/**
 * @author pdkst
 * @since 2021/4/12
 */
public class BulletCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Bullet) {
            if (((Bullet) o2).collideWith((Tank) o1)) {
                o1.die();
                o2.die();
                return true;
            }
        }
        return false;
    }
}
