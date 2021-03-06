package io.github.pdkst.tank.cor;

import io.github.pdkst.tank.model.Bullet;
import io.github.pdkst.tank.model.GameBlock;
import io.github.pdkst.tank.model.Tank;

/**
 * @author pdkst
 * @since 2021/4/12
 */
public class BulletCollider implements Collider {
    @Override
    public boolean collide(GameBlock o1, GameBlock o2) {
        if (o1 instanceof Tank && o2 instanceof Bullet) {
            if (((Bullet) o2).collideWith((Tank) o1)) {
                ((Tank) o1).die();
                ((Bullet) o2).die();
                return true;
            }
        }
        return false;
    }
}
