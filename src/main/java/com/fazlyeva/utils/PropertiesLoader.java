package com.fazlyeva.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        String path = "config.properties";
        properties.load(PropertiesLoader.class.getClassLoader().getResourceAsStream(path));

        return properties;
    }
}
