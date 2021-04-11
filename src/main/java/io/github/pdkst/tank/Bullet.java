package io.github.pdkst.tank;

import lombok.Data;

import java.awt.*;

/**
 * @author pdkst
 * @since 2021/4/11
 */
@Data
public class Bullet {
    private static final int SPEED = 20;
    public static final int WIDTH = 5;
    public static final int HEIGHT = 5;
    private int x;
    private int y;
    private Dir dir;
    private Tank tank;
    private TankFrame tankFrame;
    private Group group;
    private boolean living = true;
    Rectangle rectangle = new Rectangle();


    public Bullet(Tank tank) {
        this.tank = tank;
        this.tankFrame = tank.getTankFrame();
        this.group = tank.getGroup();
        this.x = tank.getX() + (Tank.WIDTH - WIDTH) / 2;
        this.y = tank.getY() + (Tank.HEIGHT - HEIGHT) / 2;
        this.dir = tank.getDir();
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
    }

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
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    public boolean isLiving() {
        return living && isInFrame();
    }

    private boolean isInFrame() {
        return x > 0 && y > 0 && x < tankFrame.getWidth() && y < tankFrame.getHeight();
    }

    public void collideWith(Tank tank) {
        if (tank.getGroup() == group) {
            return;
        }
        if (rectangle.intersects(tank.getRectangle())) {
            tank.die();
            this.die();
        }
    }

    private void die() {
        living = false;
    }
}
