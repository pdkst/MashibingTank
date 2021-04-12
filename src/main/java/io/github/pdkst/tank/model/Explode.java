package io.github.pdkst.tank.model;

import io.github.pdkst.tank.ResourceManager;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * @author pdkst
 * @since 2021/4/11
 */
@Getter
@Setter
public class Explode extends GameObject {
    private int step = 0;
    private boolean living = true;

    public Explode(Tank tank) {
        super(tank);
    }

    @Override
    public void paint(Graphics graphics) {
        if (living) {
            graphics.drawImage(ResourceManager.explodes[step++], x, y, null);
            if (step >= ResourceManager.explodes.length) {
                living = false;
            }
        }
    }
}
