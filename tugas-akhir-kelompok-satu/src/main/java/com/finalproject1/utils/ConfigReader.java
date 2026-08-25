package com.finalproject1.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            if (input != null) {
                properties.load(input);
            } else {
                FileInputStream fileInput = new FileInputStream("src/test/resources/config.properties");
                properties.load(fileInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load config.properties file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getTimeout() {
        return Integer.parseInt(getProperty("timeout", "15"));
    }
}
