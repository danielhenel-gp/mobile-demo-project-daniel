package com.mobile.demo.project.page;

import com.mobile.demo.project.config.AppiumDriverConfig;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WelcomePage {

    @Autowired
    private AppiumDriverConfig driverConfig;

    private final By getStartedButton =
            By.id("com.vitalpos.posmobile:id/btn_lets_go");

    public void waitForWelcomePage() {
        driverConfig.getDriver().findElement(getStartedButton);
    }
    public void clickGetStartedButton() {
        driverConfig.getDriver().findElement(getStartedButton).click();
    }
}