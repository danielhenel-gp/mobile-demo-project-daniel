package com.mobile.demo.project.config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

@Slf4j
@Component
public class AppiumDriverConfig {

    @Value("${appium.server.url}")
    private String appiumServerUrl;

    @Value("${device.name}")
    private String deviceName;

    @Value("${platform.version}")
    private String platformVersion;

    @Value("${app.path}")
    private String appPath;

    private AndroidDriver driver;

    public AndroidDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Call initDriver() first.");
        }
        return driver;
    }

    public void initDriver() {
        if (driver != null) {
            log.warn("Driver already initialized, quitting previous session.");
            quitDriver();
        }

        try {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(deviceName);
            options.setPlatformName("Android");
            options.setPlatformVersion(platformVersion);

            File appFile = new File(appPath);
            if (!appFile.exists()) {
                URL resource = getClass().getClassLoader().getResource(appPath);
                if (resource != null) {
                    appFile = new File(resource.getFile());
                } else {
                    throw new IllegalStateException("APK not found: " + appPath);
                }
            }
            options.setApp(appFile.getAbsolutePath());
            options.setAutoGrantPermissions(true);
            options.setNoReset(false);

            URL serverUrl = URI.create(appiumServerUrl + "/wd/hub").toURL();
            driver = new AndroidDriver(serverUrl, options);
            log.info("Appium driver initialized successfully.");
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Invalid Appium server URL: " + appiumServerUrl, e);
        }
    }

    public void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
                log.info("Appium driver quit successfully.");
            } catch (Exception e) {
                log.warn("Error quitting driver: {}", e.getMessage());
            } finally {
                driver = null;
            }
        }
    }
}