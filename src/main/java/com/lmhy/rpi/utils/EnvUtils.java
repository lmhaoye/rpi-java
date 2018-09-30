package com.lmhy.rpi.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class EnvUtils {
    private static Properties prop;

    static {
        prop = new Properties();
        try {
            InputStream in = EnvUtils.class.getResourceAsStream("/application.properties");
            prop.load(in);
            log.info("[env]load properties success..");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProp(String key) {
        return prop.getProperty(key);
    }

    public static String getProp(String key, String defaultValue) {
        return getProp(key, defaultValue);
    }
}
