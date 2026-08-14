package cl.framework.mobile.pages;

import cl.framework.mobile.utils.ElementActions;
import cl.framework.mobile.utils.WaitUtils;
import io.appium.java_client.android.AndroidDriver;

public class BasePage {

    protected AndroidDriver driver;
    protected ElementActions actions;
    protected WaitUtils waitUtils;

    public BasePage(AndroidDriver driver) {

        this.driver = driver;
        this.actions = new ElementActions(driver);
        this.waitUtils = new WaitUtils(driver);

    }

}
