package com.yummly.pageObjects;

import com.yummly.config.BasePageObject;
import com.yummly.config.WaitHelper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static com.yummly.config.Action.click;
import static com.yummly.config.Action.clickByText;
import static com.yummly.config.Action.type;

public class SearchScreen extends BasePageObject<SearchScreen> {

    @iOSXCUITFindBy(className = "UIATextField")
    @AndroidFindBy(id = "search_src_text")
    private MobileElement searchIcon;

    @iOSXCUITFindBy(xpath = "//*[@value='Search for Recipes']")
    @AndroidFindBy(id = "search_src_text")
    private MobileElement searchInput;

    @iOSXCUITFindBy(id = "RECIPE SUGGESTIONS")
    @AndroidFindBy(id = "suggestion")
    private List<MobileElement> suggestionPopupItems;

    @Override
    public SearchScreen init() {
        return initPage();
    }

    @Override
    protected void isLoaded() throws Error {
        WaitHelper.getInstance().waitForElementVisible(searchInput);
    }

    @Step("Type on search input = {0}")
    public void typeSearchInput(String text) {
        click(searchIcon);
        type(searchInput, text);
    }

    @Step("Select from suggestions by name = {0}")
    public SearchResultScreen selectFromSuggestionsByName(String name) {
        WaitHelper.getInstance().waitForElementsVisibleByIndex(suggestionPopupItems, 0);
        if (isIos()) {
            click(By.id(name.toLowerCase()));
        } else if (isAndroid()) {
            clickByText(suggestionPopupItems, name);
        }
        return new SearchResultScreen().init();
    }
}
