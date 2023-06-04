package com.yummly.config;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static com.yummly.config.DriverFactory.getDriver;


public abstract class BasePageObject <T extends LoadableComponent<T>> extends LoadableComponent<T>{

    public abstract T init();

    protected T initPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
        return get();
    }

    //override, if you are using deep link, to open the screen
    @Override
    protected void load() {
    }

    private String getPlatformName() {
        return getDriver().getCapabilities().getCapability("platformName").toString();
    }

    protected boolean isIos() {
        return getPlatformName().equalsIgnoreCase("ios");
    }

    protected boolean isAndroid() {
        return getPlatformName().equalsIgnoreCase("android");
    }
}
