package io.github.pdkst.tank.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.awt.*;

/**
 * @author pdkst
 * @since 2021/4/12
 */
@Data
@RequiredArgsConstructor
public abstract class GameBlock {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    private final Rectangle rectangle;

    public GameBlock(GameBlock block) {
        this.x = block.x;
        this.y = block.y;
        this.width = block.width;
        this.height = block.height;
        rectangle = new Rectangle(x, y, width, height);
    }

    public GameBlock(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rectangle = new Rectangle(x, y, width, height);
    }

    public void moveTo(int x, int y) {
        rectangle.x = x;
        rectangle.y = y;
    }

    /**
     * 绘图
     *
     * @param graphics 图形画板
     */
    public abstract void paint(Graphics graphics);
}
