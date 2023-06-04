package com.yummly.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final ThreadLocal<DriverBase> DRIVER_CONFIG_THREAD = new ThreadLocal<>();
    private static final ThreadLocal<AppiumDriver<MobileElement>> DRIVER_THREAD = new ThreadLocal<>();

    public static void initDriver(String platform) {
        if (platform.equalsIgnoreCase("ios")) {
            initDriverConfig();
            AppiumDriver<MobileElement> driver = DRIVER_CONFIG_THREAD.get().initIosDriver();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            DRIVER_THREAD.set(driver);
            driver.terminateApp("com.yummly.production");
            driver.activateApp("com.yummly.production");
        } else if (platform.equalsIgnoreCase("android")) {
            initDriverConfig();
            AppiumDriver<MobileElement> driver = DRIVER_CONFIG_THREAD.get().initAndroidDriver();
            DRIVER_THREAD.set(driver);
        } else {
            throw new RuntimeException("Platform is not specified");
        }
    }

    public static AppiumDriver<MobileElement> getDriver() {
        return DRIVER_THREAD.get();
    }

    public static void stopDriver() {
        if (DRIVER_THREAD.get()!= null) {
            DRIVER_THREAD.get().quit();
            DRIVER_THREAD.remove();
        }
        if (DRIVER_CONFIG_THREAD.get()!= null) {
            DRIVER_CONFIG_THREAD.get().stopAppiumServer();
            DRIVER_CONFIG_THREAD.remove();
        }
    }

    private static void initDriverConfig() {
        if (DRIVER_CONFIG_THREAD.get() == null) {
            DriverBase driverBase = new DriverBase();
            DRIVER_CONFIG_THREAD.set(driverBase);
        }
    }
}
