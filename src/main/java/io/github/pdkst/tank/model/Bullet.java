package io.github.pdkst.tank.model;

import io.github.pdkst.tank.Dir;
import io.github.pdkst.tank.GameModel;
import io.github.pdkst.tank.Group;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * @author pdkst
 * @since 2021/4/11
 */
@Getter
@Setter
public class Bullet extends GameObject {
    private static final int SPEED = 20;
    public static final int WIDTH = 5;
    public static final int HEIGHT = 5;
    private Dir dir;
    private Tank tank;
    private GameModel model;
    private Group group;
    private boolean living = true;


    public Bullet(Tank tank) {
        super(tank);
        this.tank = tank;
        this.model = tank.getModel();
        this.group = tank.getGroup();
        this.x = tank.getX() + (Tank.WIDTH - WIDTH) / 2;
        this.y = tank.getY() + (Tank.HEIGHT - HEIGHT) / 2;
        this.dir = tank.getDir();
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.fillOval(x, y, WIDTH, HEIGHT);
        graphics.setColor(Color.RED);
        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
        }
        moveTo(x, y);
    }

    @Override
    public boolean isLiving() {
        return living && isInFrame();
    }

    private boolean isInFrame() {
        return x > 0 && y > 0 && x < model.getWidth() && y < model.getHeight();
    }

    public boolean collideWith(Tank tank) {
        if (tank.getGroup() == group) {
            return false;
        }
        boolean collided = false;
        if (getRectangle().intersects(tank.getRectangle())) {
            tank.die();
            this.die();
            collided = true;
        }
        return collided;
    }

    @Override
    public void die() {
        living = false;
        model.removeGameObject(this);
    }
}
