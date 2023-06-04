package com.yummly.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import static com.yummly.config.Configuration.ANDROID_APP_PATH;
import static com.yummly.config.Configuration.ANDROID_DEVICE_UDID;
import static com.yummly.config.Configuration.IOS_APP_PATH;
import static com.yummly.config.Configuration.IOS_DEVICE_UDID;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.NO_SIGN;
import static io.appium.java_client.remote.MobileCapabilityType.NEW_COMMAND_TIMEOUT;

public class DriverBase {

    private AppiumDriverLocalService appiumDriverLocalService;

    public AppiumDriver<MobileElement> initAndroidDriver() {
        DesiredCapabilities androidCapabilities = getAndroidCapabilities();
        startAppiumServer(androidCapabilities);
        return new AndroidDriver<>(appiumDriverLocalService.getUrl(), androidCapabilities);
    }

    public AppiumDriver<MobileElement> initIosDriver() {
        DesiredCapabilities iosCapabilities = getIosCapabilities();
        startAppiumServer(iosCapabilities);
        return new IOSDriver<>(appiumDriverLocalService.getUrl(), iosCapabilities);
    }

    public void stopAppiumServer() {
        if (appiumDriverLocalService != null && appiumDriverLocalService.isRunning()) appiumDriverLocalService.stop();
    }

    private void startAppiumServer(DesiredCapabilities capabilities) {
        AppiumServiceBuilder appiumBuilder = new AppiumServiceBuilder();
        appiumBuilder.withIPAddress("127.0.0.1");
        appiumBuilder.usingAnyFreePort();
        appiumBuilder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub");
        appiumBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        appiumBuilder.withCapabilities(capabilities);
        appiumBuilder.withStartUpTimeOut(3, TimeUnit.MINUTES);
        appiumBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumBuilder);
        appiumDriverLocalService.start();
    }

    private DesiredCapabilities getIosCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability("appium:forceAppLaunch", true);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCuiTest");
        capabilities.setCapability(MobileCapabilityType.UDID, IOS_DEVICE_UDID);
        capabilities.setCapability(MobileCapabilityType.APP, IOS_APP_PATH);
        capabilities.setCapability("appPackage", "com.facebook.wda.integrationApp");

        AppiumServiceBuilder appiumBuilder = new AppiumServiceBuilder();
        appiumBuilder.withIPAddress("127.0.0.1");
        appiumBuilder.usingAnyFreePort();
        appiumBuilder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub");
        return capabilities;
    }




     private DesiredCapabilities getAndroidCapabilities() {
         DesiredCapabilities capabilities = new DesiredCapabilities();
         capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
         capabilities.setCapability("appium:forceAppLaunch", true);
         capabilities.setCapability("appium:noReset", true);
         capabilities.setCapability("appium:ignoreUnimportantViews", false);
         capabilities.setCapability("appium:disableAndroidWatchers", true);
         capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
         capabilities.setCapability(MobileCapabilityType.UDID, ANDROID_DEVICE_UDID);
         capabilities.setCapability(MobileCapabilityType.APP, ANDROID_APP_PATH);
         capabilities.setCapability("appium:appWaitActivity", "*");
         capabilities.setCapability(APP_ACTIVITY, ".feature.splash.SplashActivity");
         capabilities.setCapability(NO_SIGN, true);
         capabilities.setCapability(NEW_COMMAND_TIMEOUT, 300);
         return capabilities;
     }
}
