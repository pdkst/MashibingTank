package io.github.pdkst.tank.model;

import io.github.pdkst.tank.GameModel;

/**
 * @author pdkst
 * @since 2021/4/12
 */
public abstract class GameObject extends GameBlock {

    public GameObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public GameObject(GameBlock block) {
        super(block);
    }

    public abstract boolean isLiving();

    public void die() {
        GameModel.getInstance().removeBlock(this);
    }
}
