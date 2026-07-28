package com.mobile.demo.project.cucumber.hooks;

import com.mobile.demo.project.config.AppiumDriverConfig;
import com.mobile.demo.project.page.ApkManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class AppiumHooks {

    @Autowired
    private ApkManager apkManager;

    @Autowired
    private AppiumDriverConfig driverConfig;

    @Before
    public void setUp() {
        driverConfig.initDriver();
    }

    @After
    public void tearDown() {
        if (apkManager.isApkInstalled()) {
            apkManager.removeApk();
        }
        driverConfig.quitDriver();
    }
}
