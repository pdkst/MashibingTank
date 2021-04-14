package io.github.pdkst.tank.cor;

import io.github.pdkst.tank.model.GameBlock;
import io.github.pdkst.tank.model.Tank;

/**
 * @author pdkst
 * @since 2021/4/14
 */
public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameBlock o1, GameBlock o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            if (o1.getRectangle().intersects(o2.getRectangle())) {
                ((Tank) o1).back();
                ((Tank) o2).back();
            }
        }
        return false;
    }
}
