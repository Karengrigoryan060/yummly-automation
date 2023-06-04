package com.yummly.pageObjects;

import com.yummly.enums.Side;
import com.yummly.config.BasePageObject;
import com.yummly.config.WaitHelper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;

import java.util.List;

import static com.yummly.config.Action.click;
import static com.yummly.config.Action.getText;
import static com.yummly.config.Action.swipeUntilElementDisplayed;

public class ProductDetailsScreen extends BasePageObject<ProductDetailsScreen> {

    @iOSXCUITFindBy(className = "Overview")
    @AndroidFindBy(xpath = "//*[@text='Overview']")
    private MobileElement overviewTab;

    @iOSXCUITFindBy(id = "Ingredients")
    @AndroidFindBy(xpath = "//*[@text='Ingredients']")
    private MobileElement ingredientsTab;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name  ==  'YPL.FFL.F0003-009' OR name  == 'YPL.FFL.F0003-00A'`]")
    @AndroidFindBy(id = "related_recipe_favorite")
    private List<MobileElement> ingredientItems;

    @iOSXCUITFindBy(id = "YPL.FFL.F0003-00E")
    @AndroidFindBy(id = "recipe_back")
    private MobileElement backButton;

    @iOSXCUITFindBy(id = "YPL.FFL.F0003-009")
    @AndroidFindBy(id = "related_recipe_favorite")
    private List<MobileElement> addItemIcon;

    @iOSXCUITFindBy(id = "YPL.FFL.F0003-00A")
    @AndroidFindBy(id = "related_recipe_favorite")
    private List<MobileElement> removeItemIcon;

    @iOSXCUITFindBy(className = "UIAScrollView")
    @AndroidFindBy(id = "ingredient_line")
    private List<MobileElement> scrollBar;

    @Override
    public ProductDetailsScreen init() {
        return initPage();
    }

    @Override
    protected void isLoaded() throws Error {
        WaitHelper.getInstance().waitForElementVisible(overviewTab);
    }

    public void tapOnIngredientsTab() {
        click(ingredientsTab);
    }

    @Step("Swipe element")
    public void swipeElement() {
        MobileElement webElement = WaitHelper.getInstance().waitForElementsVisibleByIndex(scrollBar, 2);
        swipeUntilElementDisplayed(webElement, Side.UP);
    }

    @Step("Tap on last element")
    public void tapLastElement() {
        if (isIos()) {
            MobileElement element = WaitHelper.getInstance().waitForElementsVisibleByIndex(scrollBar, 2);
            swipeUntilElementDisplayed(element, Side.UP);
            click(addItemIcon, addItemIcon.size() - 1);
        } else if (isAndroid()) {
            MobileElement element = WaitHelper.getInstance().waitForElementsVisibleByIndex(scrollBar, 0);
            swipeUntilElementDisplayed(element, Side.UP);
            click(addItemIcon, addItemIcon.size() - 1);
        }
    }
    @Step("Swipe down")
    public void swipeDownScrollMenu() {
        MobileElement webElement = WaitHelper.getInstance().waitForElementsVisibleByIndex(scrollBar, 2);
        swipeUntilElementDisplayed(webElement, Side.DOWN);
    }

    @Step("Tap on back button")
    public SearchResultScreen tapOnBackButton() {
        click(backButton);
        return new SearchResultScreen().init();
    }

    @Step("Checking is last item selected")
    public boolean isLastItemSelected() {
        if (isIos())
            return getText(ingredientItems, ingredientItems.size() - 1).equalsIgnoreCase("YPL.FFL.F0003-00A");
        else if (isAndroid()) {
            // for icon there is no any indicator to catch is it selected or no. Would be great to request selector from the dev team
            return false;
        } else throw new RuntimeException("Platform is incorrect");
    }
}
