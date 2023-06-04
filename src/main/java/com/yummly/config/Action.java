package com.yummly.config;

import com.yummly.enums.Side;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

import static com.yummly.config.DriverFactory.getDriver;
import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class Action {

    public static void click(WebElement element) {
        WaitHelper.getInstance().waitForElementClickable(element).click();
    }

    public static void clickByCoordinate(MobileElement element) {
        Point center = getCenter(element);
        new TouchAction<>(getDriver())
                .tap(new PointOption().withCoordinates(center.getX(), center.getY()))
                .release()
                .perform();
    }

    public static void clickByCoordinate(By by) {
        Point center = getCenter(getDriver().findElement(by));
        new TouchAction<>(getDriver())
                .tap(new PointOption().withCoordinates(center.getX(), center.getY()))
                .release()
                .perform();
    }

    public static void click(List<MobileElement> elements, int index) {
        WaitHelper.getInstance().waitForElementsVisibleByIndex(elements, index).click();
    }

    public static void click(By by, int index) {
        List<MobileElement> elements = getDriver().findElements(by);
        WaitHelper.getInstance().waitForElementsVisibleByIndex(elements, index).click();
    }

    public static void click(By by) {
        WaitHelper.getInstance().waitForElementClickable(by).click();
    }

    public static void clickByText(List<MobileElement> elements, String text) {
        WaitHelper.getInstance().waitForElementsVisibleByIndex(elements, 0);
        elements.stream().filter(element -> element.getText().equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow()
                .click();
    }

    public static int getIndexByText(By by, String text) {
        List<MobileElement> elements = getDriver().findElements(by);
        WaitHelper.getInstance().waitForElementsVisibleByIndex(elements, 0);
        return IntStream.range(0, elements.size())
                .filter(i -> text.equalsIgnoreCase(elements.get(i).getText()))
                .findFirst().orElseThrow();
    }

    public static void clickByText(By by, String text) {
        List<MobileElement> elements = getDriver().findElements(by);
        WaitHelper.getInstance().waitForElementsVisibleByIndex(elements, 0);
        elements.stream().filter(element -> element.getText().equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow()
                .click();
    }

    public static void type(WebElement element, String text) {
        WaitHelper.getInstance().waitForElementClickable(element).sendKeys(text);
    }

    public static String getText(WebElement element) {
        return WaitHelper.getInstance().waitForElementVisible(element).getText();
    }

    public static String getText(List<MobileElement> elements, int index) {
        return WaitHelper.getInstance().waitForElementsVisibleByIndex(elements, index).getText();
    }

    public static void swipeUntilElementDisplayed(MobileElement element, Side side) {
        swipe(element, side, 500);
    }

    public static void swipe(
            MobileElement element, Side side, int percent) {
        switch (side) {
            case LEFT:
                drawLine(element, percent, 0, -percent, 0);
                break;
            case RIGHT:
                drawLine(element, -percent, 0, percent, 0);
                break;
            case UP:
                drawLine(element, 0, Math.min(percent, 85), 0, -percent);
                break;
            case DOWN:
                drawLine(element, 0, Math.max(-percent, -85), 0, percent);
                break;
        }
    }

    public static void drawLine(
            MobileElement element,
            int startX,
            int startY,
            int endX,
            int endY) {
        WaitHelper.getInstance().waitForElementVisible(element);
        int x = getCenter(element).getX();
        int y = getCenter(element).getY();
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();
        int startOffsetX = width / 2 * startX / 101;
        int startOffsetY = height / 2 * startY / 101;
        int endOffsetX = width / 2 * endX / 101;
        int endOffsetY = height / 2 * endY / 101;
        swipeByCoordinate(
                x + startOffsetX, y + startOffsetY, x + endOffsetX, y + endOffsetY, 10);
    }

    public static void swipeByCoordinate(
            int startX, int startY, int endX, int endY, int swipingTime) {
        PointOption pointOption = new PointOption();
        new TouchAction(getDriver())
                .press(pointOption.withCoordinates(startX, startY))
                .waitAction(waitOptions(Duration.ofMillis(swipingTime)))
                .moveTo(pointOption.withCoordinates(endX, endY))
                .release()
                .perform();
    }

    public static Point getCenter(MobileElement element) {
        Rectangle rectangle = element.getRect();
        int x = rectangle.getX() + rectangle.getWidth() / 2;
        int y = rectangle.getY() + rectangle.getHeight() / 2;
        return new Point(x, y);
    }
}
