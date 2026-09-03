package com.mobile.demo.project.cucumber.steps;

import com.mobile.demo.project.page.ApkManager;
import com.mobile.demo.project.page.LoginScreen;
import com.mobile.demo.project.page.WelcomePage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class MainSteps {

    @Autowired
    private ApkManager apkManager;

    @Autowired
    private WelcomePage welcomePage;

    @Autowired
    private LoginScreen loginScreen;

    @Given("The user installed the application")
    public void given_TheUserInstallTheApplication() {
        Assert.assertTrue(apkManager.isApkInstalled());
    }

    @Given("The user launched the application")
    public void theUserLaunchedTheApplication() {
        welcomePage.clickGetStartedButton();
    }

    @Given("The user is on the login screen")
    public void theUserIsOnTheLoginScreen() {
        loginScreen.waitForLoginScreen();
    }

    @And("The user enters valid credentials")
    public void theUserEntersValidCredentials() {
        loginScreen.enterAccountId("111707");
        loginScreen.enterUsername("amationDev");
        loginScreen.enterPassword("P@ssword08");
    }

    @When("The user clicks on the login button")
    public void theUserClicksOnTheLoginButton() {
        loginScreen.clickLoginButton();
    }

    @Then("The user should be logged in successfully")
    public void theUserShouldBeLoggedInSuccessfully() {
        loginScreen.waitForSelectLocationScreen();
        String pageTitle = loginScreen.getPageTitle();
        Assert.assertEquals("SELECT A LOCATION", pageTitle);
    }
}