package com.yummly.config;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class Configuration {

    public static final String ANDROID_APP_PATH;
    public static final String ANDROID_DEVICE_UDID;
    public static final String IOS_APP_PATH;
    public static final String IOS_DEVICE_UDID;
    public static final int WAIT_TIMEOUT;
    public static final int RETRY_COUNT;
    private static final Properties configs;

    // Read property file only once
    static {
        configs = new Properties();
        final var loader = Thread.currentThread().getContextClassLoader();
        try (InputStream file = loader.getResourceAsStream("config.properties")) {
            configs.load(file);
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        ANDROID_APP_PATH = getProperty("android.app.path");
        ANDROID_DEVICE_UDID = getProperty("android.device.udid");
        IOS_APP_PATH = getProperty("ios.app.path");
        IOS_DEVICE_UDID = getProperty("ios.device.udid");
        WAIT_TIMEOUT = Integer.parseInt(getProperty("element.wait.timeout"));
        RETRY_COUNT = Integer.parseInt(getProperty("retry.count"));
    }

    private Configuration() {
    }

    /**
     * Trying to get property from the System by key
     * If in System there is no, getting from config.property file
     *
     * @param key of property
     * @return value of the property
     */
    public static String getProperty(String key) {
        if (System.getProperty(key) == null || System.getProperty(key).isEmpty()) {
            String property = configs.getProperty(key);
            log.info("Getting property " + key + ": " + property);
            return property;
        } else {
            String property = System.getProperty(key);
            log.info("Getting property " + key + ": " + property);
            return property;
        }
    }

    /**
     * Trying to get property from the System by key
     * If in System there is no, getting from config.property file
     * otherwise returns default value
     *
     * @param key          of property
     * @param defaultValue is the value which will return if the property is empty
     * @return value of the property
     */
    public static String getProperty(String key, String defaultValue) {
        if (System.getProperty(key) == null || System.getProperty(key).isEmpty()) {
            if (configs.getProperty(key) == null || configs.getProperty(key).isEmpty()) {
                return defaultValue;
            } else {
                return configs.getProperty(key);
            }
        } else {
            return System.getProperty(key);
        }
    }
}