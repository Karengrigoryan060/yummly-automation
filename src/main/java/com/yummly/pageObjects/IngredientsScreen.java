package com.yummly.pageObjects;

import com.yummly.config.BasePageObject;
import com.yummly.config.WaitHelper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.yummly.config.Action.click;
import static com.yummly.config.Action.clickByCoordinate;
import static com.yummly.config.Action.getIndexByText;

public class IngredientsScreen extends BasePageObject<IngredientsScreen> {

    @iOSXCUITFindBy(id = "Shop")
    @AndroidFindBy(xpath = "//*[@text='Shop']")
    private MobileElement shopButton;

    @Override
    public IngredientsScreen init() {
        return initPage();
    }

    @Override
    protected void isLoaded() throws Error {
        WaitHelper.getInstance().waitForElementVisible(shopButton);
    }

    public void tapShopButton() {
        click(shopButton);
    }

    @Step("Tap on signup close button")
    public void tapSignupCloseButton() {
        if (isIos())
            clickByCoordinate(By.id("HomeButtonTabBar"));
        else if (isAndroid())
            click(By.id("alert_close_button"));
    }

    @Step("Tap Shop Item By Name = {0}")
    public ProductDetailsScreen tapShopItemByName(String name) {
        if (isIos())
            click(By.id(name));
        else if (isAndroid()) {
            By by = By.id("item_title");
            int itemIndex = getIndexByText(by, name);
            click(By.id("expand_menu"), itemIndex);
            click(By.xpath("//*[@text='RECIPE']"));
        }
        return new ProductDetailsScreen().init();
    }
}
