package com.yummly.pageObjects;

import com.yummly.components.FooterComponent;
import com.yummly.config.BasePageObject;
import com.yummly.config.WaitHelper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.yummly.config.Action.click;
import static com.yummly.config.Action.clickByText;

public class SearchResultScreen extends BasePageObject<SearchResultScreen> {

    @iOSXCUITFindBy(xpath = "Tiramisu or Tiramisu Cake")
    @AndroidFindBy(id = "item_title")
    private List<MobileElement> resultItems;

    @iOSXCUITFindBy(id = "Search Content View")
    @AndroidFindBy(id = "search_src_text")
    private WebElement rootElement;

    @Override
    public SearchResultScreen init() {
        return initPage();
    }

    @Override
    protected void isLoaded() throws Error {
        WaitHelper.getInstance().waitForElementVisible(rootElement);
    }

    @Step("Tap search result item by index = {0}")
    public ProductDetailsScreen tapSearchResultItemByIndex(String name) {
        if (isIos()) {
            click(By.id(name));
        } else if (isAndroid()) {
            clickByText(resultItems, name);
        }
        return new ProductDetailsScreen().init();
    }

    public FooterComponent getFooter() {
        return new FooterComponent().init();
    }
}
