package com.mobile.demo.project.page;


import com.mobile.demo.project.config.AppiumDriverConfig;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class LoginScreen {

    @Autowired
    private AppiumDriverConfig driverConfig;


    private final By accountIdField =
            By.id("com.vitalpos.posmobile:id/fld_account_id");

    private final By usernameField =
            By.id("com.vitalpos.posmobile:id/fld_username");

    private final By passwordField =
            By.id("com.vitalpos.posmobile:id/fld_password");

    private final By loginButton =
            By.id("com.vitalpos.posmobile:id/btn_log_in");

    private final  By selectLocationTitle =
            By.id("com.vitalpos.posmobile:id/select_item_title");

    public void waitForLoginScreen() {
        WebDriverWait wait = new WebDriverWait(driverConfig.getDriver(), Duration.ofSeconds(10));
        wait.until(d -> d.findElement(accountIdField).isDisplayed());
    }

    public void enterAccountId(String accountId) {
        driverConfig.getDriver().findElement(accountIdField).sendKeys(accountId);
    }

    public void enterUsername(String username) {
        driverConfig.getDriver().findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driverConfig.getDriver().findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driverConfig.getDriver().findElement(loginButton).click();
    }

    public void waitForSelectLocationScreen() {
        WebDriverWait wait = new WebDriverWait( driverConfig.getDriver(), Duration.ofSeconds(10));
        wait.until(d -> d.findElement(selectLocationTitle).isDisplayed());
    }

    public String getPageTitle() {
        return  driverConfig.getDriver().findElement(selectLocationTitle).getText();
    }

}