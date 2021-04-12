package io.github.pdkst.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author pdkst
 * @since 2021/4/11
 */
public class PropertyManager {
    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyManager.class.getResourceAsStream("/tank.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getProperty(String key) {
        if (properties == null) {
            return null;
        }
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        if (properties == null) {
            return -1;
        }
        final String property = properties.getProperty(key);
        return Integer.parseInt(property);
    }

    public static void main(String[] args) {
        System.out.println(getProperty("initTankCount"));
    }
}
