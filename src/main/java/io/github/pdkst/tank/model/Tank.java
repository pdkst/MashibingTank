package io.github.pdkst.tank.model;

import io.github.pdkst.tank.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author pdkst
 * @since 2021/4/11
 */
@Getter
@Setter
public class Tank extends GameObject {
    public static final int SPEED_INIT = 10;
    public static final int WIDTH = ResourceManager.tankImageUp.getWidth();
    public static final int HEIGHT = ResourceManager.tankImageUp.getHeight();
    private MyKeyListener myKeyListener = new MyKeyListener(this);
    private GameModel model;
    private Dir dir = Dir.DOWN;
    private int speed = SPEED_INIT;
    private boolean living = true;
    private Group group = Group.BAD;
    private Random random = new Random();

    public Tank(GameModel model, int x, int y) {
        super(x, y, WIDTH, HEIGHT);
        this.model = model;
        model.getTankFrame().addKeyListener(getMyKeyListener());
    }

    @Override
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
        moveTo(x, y);
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
        model.addGameObject(bullet);
    }

    @Override
    public boolean isLiving() {
        return living;
    }

    @Override
    public void die() {
        living = false;
        model.addGameObject(new Explode(this));
        model.getTankFrame().removeKeyListener(myKeyListener);
        model.removeGameObject(this);
    }
}
