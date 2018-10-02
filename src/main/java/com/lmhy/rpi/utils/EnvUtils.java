package com.lmhy.rpi.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class EnvUtils {
    private static Logger log = LoggerFactory.getLogger(EnvUtils.class);
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

   /* public static void setProp(String key,String value){
        try{
            OutputStream fos = new FileOutputStream();
            prop.store();

        }catch (Exception e){
            log.error("set error",e);
        }
    }*/
}
