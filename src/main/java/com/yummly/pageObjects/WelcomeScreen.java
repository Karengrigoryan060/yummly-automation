package com.yummly.pageObjects;

import com.yummly.config.BasePageObject;
import com.yummly.config.WaitHelper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;

import static com.yummly.config.Action.click;
import static com.yummly.config.DriverFactory.getDriver;

public class WelcomeScreen extends BasePageObject<WelcomeScreen> {
    @iOSXCUITFindBy(id = "I'll do it later")
    @AndroidFindBy(id = "skip_view")
    private MobileElement skipSignInButton;

    @Override
    public WelcomeScreen init() {
        return initPage();
    }

    @Override
    protected void isLoaded() throws Error {
        getDriver();
        WaitHelper.getInstance().waitForElementVisible(skipSignInButton);
    }

    @Step("Tap on skip button")
    public HomeScreen tapOnSkipButton() {
        click(skipSignInButton);
        return new HomeScreen().init();
    }
}
