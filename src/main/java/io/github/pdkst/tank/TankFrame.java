package io.github.pdkst.tank;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.KeyListener;

/**
 * @author pdkst
 * @since 2021/4/11
 */
@Slf4j
public class TankFrame extends GameFrame {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    public TankFrame() throws HeadlessException {
        super(GAME_WIDTH, GAME_HEIGHT);
        setTitle("坦克大战");
        GameModel model = GameModel.getInstance();
        KeyListener keyListener = new MyKeyListener(model.getMyTank());
        addKeyListener(keyListener);
    }

    @Override
    public void paint(Graphics g) {
        GameModel model = GameModel.getInstance();
        if (model != null) {
            model.paint(g);
        }
    }
}
