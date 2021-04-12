package io.github.pdkst.tank;

import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author pdkst
 * @since 2021/4/11
 */
@Data
public class Tank {
    public static final int SPEED_INIT = 10;
    public static final int WIDTH = ResourceManager.tankImageUp.getWidth();
    public static final int HEIGHT = ResourceManager.tankImageUp.getHeight();
    private MyKeyListener myKeyListener = new MyKeyListener(this);
    private GameModel model;
    private int x;
    private int y;
    private Dir dir = Dir.DOWN;
    private int speed = SPEED_INIT;
    private int width = WIDTH;
    private int height = HEIGHT;
    private boolean living = true;
    private Group group = Group.BAD;
    private Random random = new Random();
    Rectangle rectangle = new Rectangle();

    public Tank(GameModel model, int x, int y) {
        this.model = model;
        this.x = x;
        this.y = y;
        model.getTankFrame().addKeyListener(getMyKeyListener());
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
    }

    public void paint(Graphics graphics) {
        final Color color = graphics.getColor();
        final Graphics2D graphics2D = (Graphics2D) graphics;
        BufferedImage tankImage = ResourceManager.tankImageUp;
        switch (dir) {
            case LEFT:
                tankImage = ResourceManager.tankImageLeft;
                break;
            case RIGHT:
                tankImage = ResourceManager.tankImageRight;
                break;
            case UP:
                tankImage = ResourceManager.tankImageUp;
                break;
            case DOWN:
                tankImage = ResourceManager.tankImageDown;
                break;
            default:
        }
        graphics2D.drawImage(tankImage, x, y, null);
        graphics.setColor(color);
        if (group == Group.GOOD) {
            if (myKeyListener.isMoving()) {
                move();
            }
        } else if (group == Group.BAD) {
            if (random.nextInt(100) > 95) {
                this.fire();
            }
            if (random.nextInt(100) > 95) {
                dir = Dir.values()[random.nextInt(4)];
            }
            move();
        }
        boundCheck();
    }

    private void boundCheck() {
        if (x < 0) {
            x = 0;
        }
        if (y < 30) {
            y = 30;
        }
        if (x > model.getWidth() - width) {
            x = model.getWidth() - width;
        }
        if (y > model.getHeight() - height) {
            y = model.getHeight() - height;
        }
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            default:
        }
    }

    public void fire() {
        final Bullet bullet = new Bullet(this);
        model.addBullet(bullet);
    }

    public boolean isLiving() {
        return living;
    }

    public void die() {
        living = false;
        model.addExplode(new Explode(this));
    }
}
