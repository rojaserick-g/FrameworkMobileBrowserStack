package cl.framework.mobile.utils;

import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private final WebDriverWait wait;

    public WaitUtils(AndroidDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebDriverWait getWait() {
        return wait;
    }

}