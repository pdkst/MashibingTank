package io.github.pdkst.tank.cor;

import io.github.pdkst.tank.model.GameBlock;

import java.util.LinkedList;
import java.util.List;

/**
 * @author pdkst
 * @since 2021/4/14
 */
public class ColliderChain implements Collider {
    private List<Collider> colliderList = new LinkedList<>();

    public ColliderChain() {
        add(new BulletCollider());
        add(new TankTankCollider());
        add(new BulletWallCollider());
        add(new TankWallCollider());
    }

    public void add(Collider collider) {
        colliderList.add(collider);
    }

    @Override
    public boolean collide(GameBlock o1, GameBlock o2) {
        for (final Collider collider : colliderList) {
            if (collider.collide(o1, o2)) {
                return true;
            }
        }
        return false;
    }
}
