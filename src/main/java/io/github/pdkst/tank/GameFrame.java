package io.github.pdkst.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author pdkst
 * @since 2021/4/11
 */
public class GameFrame extends Frame {
    private Image offScreenImage;

    public GameFrame(int gameWidth, int gameHeight) {
        setSize(gameWidth, gameHeight);
        setResizable(false);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * 双缓冲，防止画面闪烁
     * <p>repaint -> update
     * <p>截获组件先画到图像中，再一次性画到graphics
     *
     * @param graphics 图像
     */
    @Override
    public void update(Graphics graphics) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(getWidth(), getHeight());
        }
        final Graphics offScreenImageGraphics = offScreenImage.getGraphics();
        final Color color = offScreenImageGraphics.getColor();
        offScreenImageGraphics.setColor(Color.BLACK);
        offScreenImageGraphics.fillRect(0, 0, getWidth(), getHeight());
        offScreenImageGraphics.setColor(color);
        print(offScreenImageGraphics);
        graphics.drawImage(offScreenImage, 0, 0, null);
    }
}
