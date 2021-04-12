package io.github.pdkst.tank;

import lombok.Data;

import java.awt.*;

/**
 * @author pdkst
 * @since 2021/4/11
 */
@Data
public class Explode {
    private int x;
    private int y;
    private int step = 0;
    private boolean living = true;

    public Explode(Tank tank) {
        this.x = tank.getX();
        this.y = tank.getY();
    }

    public void paint(Graphics graphics) {
        if (living) {
            graphics.drawImage(ResourceManager.explodes[step++], x, y, null);
            if (step >= ResourceManager.explodes.length) {
                living = false;
            }
        }
    }
}
