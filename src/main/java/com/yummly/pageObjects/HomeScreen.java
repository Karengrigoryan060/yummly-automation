package com.yummly.pageObjects;

import com.yummly.components.FooterComponent;
import com.yummly.config.BasePageObject;
import com.yummly.config.WaitHelper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomeScreen extends BasePageObject<HomeScreen> {
    @iOSXCUITFindBy(id = "JUST FOR YOU")
    @AndroidFindBy(xpath = "//*[@text='Weekly recommendations']")
    private MobileElement indicatorElement;

    @Override
    public HomeScreen init() {
        return initPage();
    }

    @Override
    protected void isLoaded() throws Error {
        WaitHelper.getInstance().waitForElementVisible(indicatorElement);
    }

    public FooterComponent getFooter() {
        return new FooterComponent().init();
    }
}