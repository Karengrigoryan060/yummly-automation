package com.yummly.components;

import com.yummly.config.BasePageObject;
import com.yummly.config.WaitHelper;
import com.yummly.pageObjects.HomeScreen;
import com.yummly.pageObjects.IngredientsScreen;
import com.yummly.pageObjects.SearchScreen;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import static com.yummly.config.Action.click;

public class FooterComponent extends BasePageObject<FooterComponent> {
    @iOSXCUITFindBy(id = "MealPlanButtonTabBar")
    @AndroidFindBy(id = "nav_graph_user_ingredients")
    private MobileElement ingredientsButton;
    @iOSXCUITFindBy(id = "HomeButtonTabBar")
    @AndroidFindBy(id = "nav_graph_home")
    private MobileElement homeButton;
    @iOSXCUITFindBy(id = "SearchButtonTabBar")
    @AndroidFindBy(id = "nav_graph_search")
    private MobileElement searchButton;

    @Override
    public FooterComponent init() {
        return initPage();
    }

    @Override
    protected void isLoaded() throws Error {
        WaitHelper.getInstance().waitForElementVisible(homeButton);
    }

    public HomeScreen tapOnHomeButton() {
        click(homeButton);
        return new HomeScreen().init();
    }

    public SearchScreen tapOnSearchButton() {
        click(searchButton);
        return new SearchScreen().init();
    }

    public IngredientsScreen tapOnBasketButton() {
        click(ingredientsButton);
        return new IngredientsScreen().init();
    }
}