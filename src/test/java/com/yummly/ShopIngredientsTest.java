package com.yummly;

import com.yummly.base.BaseTest;
import com.yummly.pageObjects.IngredientsScreen;
import com.yummly.pageObjects.HomeScreen;
import com.yummly.pageObjects.ProductDetailsScreen;
import com.yummly.pageObjects.SearchResultScreen;
import com.yummly.pageObjects.SearchScreen;
import com.yummly.pageObjects.WelcomeScreen;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ShopIngredientsTest extends BaseTest {

    @Test
    public void tiramisuIngredientTest() {
        String productName = "tiramisu";

        WelcomeScreen welcomeScreen = new WelcomeScreen().init();
        HomeScreen homeScreen = welcomeScreen.tapOnSkipButton();
        SearchScreen searchScreen = homeScreen.getFooter().tapOnSearchButton();
        searchScreen.typeSearchInput(productName);
        SearchResultScreen searchResultScreen = searchScreen.selectFromSuggestionsByName(productName);
        ProductDetailsScreen productDetailsScreen = searchResultScreen.tapSearchResultItemByIndex("Tiramisu or Tiramisu Cake");
        productDetailsScreen.tapOnIngredientsTab();
        productDetailsScreen.tapLastElement();
        productDetailsScreen.swipeDownScrollMenu();
        searchResultScreen = productDetailsScreen.tapOnBackButton();
        IngredientsScreen ingredientsScreen = searchResultScreen.getFooter().tapOnBasketButton();
        ingredientsScreen.tapShopButton();
        ingredientsScreen.tapSignupCloseButton();
        productDetailsScreen = ingredientsScreen.tapShopItemByName("Tiramisu or Tiramisu Cake");
        productDetailsScreen.tapOnIngredientsTab();
        productDetailsScreen.swipeElement();
        assertTrue(productDetailsScreen.isLastItemSelected(), "Last item is not selected");
    }

}
