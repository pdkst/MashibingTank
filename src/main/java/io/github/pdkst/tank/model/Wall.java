package io.github.pdkst.tank.model;

import java.awt.*;

/**
 * @author pdkst
 * @since 2021/4/14
 */
public class Wall extends GameBlock {
    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void paint(Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.GRAY);
        graphics.drawRect(x, y, width, height);
        graphics.setColor(color);
    }
}
