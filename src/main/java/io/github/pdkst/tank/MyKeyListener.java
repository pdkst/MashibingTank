package io.github.pdkst.tank;

import lombok.Data;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author pdkst
 */
@Data
public class MyKeyListener extends KeyAdapter {
    private final Tank tank;
    private boolean bl = false;
    private boolean br = false;
    private boolean bu = false;
    private boolean bd = false;
    private Dir dir = Dir.DOWN;

    @Override
    public void keyPressed(KeyEvent e) {
        this.updateStatus(e.getKeyCode(), true);
        if (tank.getGroup() == Group.GOOD) {
            tank.setDir(this.getMainTankDir());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.updateStatus(e.getKeyCode(), false);
        if (tank.getGroup() == Group.GOOD) {
            tank.setDir(this.getMainTankDir());
        }
    }

    private Dir getMainTankDir() {
        if (bl) {
            dir = Dir.LEFT;
        }
        if (br) {
            dir = Dir.RIGHT;
        }
        if (bu) {
            dir = Dir.UP;
        }
        if (bd) {
            dir = Dir.DOWN;
        }
        return dir;
    }

    public boolean isMoving() {
        return bl || br || bu || bd;
    }

    private void updateStatus(final int key, final boolean status) {
        switch (key) {
            case KeyEvent.VK_LEFT:
                bl = status;
                break;
            case KeyEvent.VK_RIGHT:
                br = status;
                break;
            case KeyEvent.VK_UP:
                bu = status;
                break;
            case KeyEvent.VK_DOWN:
                bd = status;
                break;
            case KeyEvent.VK_CONTROL:
                tank.fire();
                break;
            default:
        }
    }
}