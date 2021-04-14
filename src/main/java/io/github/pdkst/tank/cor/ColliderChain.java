package io.github.pdkst.tank.cor;

import io.github.pdkst.tank.model.GameObject;

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
    }

    public void add(Collider collider) {
        colliderList.add(collider);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (final Collider collider : colliderList) {
            if (collider.collide(o1, o2)) {
                return true;
            }
        }
        return false;
    }
}
