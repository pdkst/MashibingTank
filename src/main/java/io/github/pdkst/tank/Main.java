package io.github.pdkst.tank;

import java.util.concurrent.TimeUnit;

/**
 * @author pdkst
 * @since 2021/4/11
 */
public class Main {

    public static void main(String[] args) {
        final TankFrame tankFrame = new TankFrame();
        final String initTankCount = PropertyManager.getProperty("initTankCount");
        for (int i = 0; i < Integer.parseInt(initTankCount); i++) {
            final Tank tank = new Tank(tankFrame, 100 * i, 300);
            tankFrame.tanks.add(tank);
        }
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
                tankFrame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
