package io.github.pdkst.tank;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;

/**
 * @author pdkst
 * @since 2021/4/11
 */
@Slf4j
public class TankFrame extends GameFrame {
    @Getter
    final GameModel model = new GameModel(this);

    public TankFrame() throws HeadlessException {
        super(800, 600);
        setTitle("坦克大战");
    }

    @Override
    public void paint(Graphics g) {
        if (model != null) {
            model.paint(g);
        }
    }
}
