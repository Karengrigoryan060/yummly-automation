package com.yummly.config;

import io.appium.java_client.MobileElement;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.yummly.config.Configuration.WAIT_TIMEOUT;
import static com.yummly.config.DriverFactory.getDriver;

public class WaitHelper {

    private final WebDriverWait wait;

    private WaitHelper() {
        wait = new WebDriverWait(getDriver(), WAIT_TIMEOUT);
    }

    public static WaitHelper getInstance() {
        return new WaitHelper();
    }

    public WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        }catch (Exception e) {
            return false;
        }
    }

    public MobileElement waitForElementsVisibleByIndex(List<MobileElement> element, int index) {
        return wait.until(new ExpectedCondition<>() {
            @NullableDecl
            @Override
            public MobileElement apply(@NullableDecl WebDriver webDriver) {
                try {
                    return element.get(index);
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }
}
