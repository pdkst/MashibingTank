package io.github.pdkst.tank.cor;

import io.github.pdkst.tank.model.Bullet;
import io.github.pdkst.tank.model.GameBlock;
import io.github.pdkst.tank.model.Tank;

/**
 * @author pdkst
 * @since 2021/4/14
 */
public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameBlock o1, GameBlock o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            if (o1.getRectangle().intersects(o2.getRectangle())) {
                ((Bullet) o1).die();
            }
        }
        return false;
    }
}
