package io.github.pdkst.tank;

import io.github.pdkst.tank.model.Bullet;
import io.github.pdkst.tank.model.GameObject;
import io.github.pdkst.tank.model.Tank;

import java.util.function.Consumer;

/**
 * @author pdkst
 * @since 2021/4/12
 */
public class BulletCollider implements Collider<GameObject> {
    @Override
    public void collide(GameObject o1, GameObject o2, Consumer<Void> consumer) {
        if (o1 instanceof Tank && o2 instanceof Bullet) {
            if (((Bullet) o2).collideWith((Tank) o1)) {
                consumer.accept(null);
            }
        }
    }
}
