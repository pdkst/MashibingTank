package io.github.pdkst.tank;

import java.util.concurrent.TimeUnit;

/**
 * @author pdkst
 * @since 2021/4/11
 */
public class Main {

    public static void main(String[] args) {
        final TankFrame tankFrame = new TankFrame();

        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(50);
                tankFrame.repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
