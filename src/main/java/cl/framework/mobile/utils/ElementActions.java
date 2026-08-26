package cl.framework.mobile.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ElementActions {

    private final AndroidDriver driver;

    public ElementActions(AndroidDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void write(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public boolean isDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

}