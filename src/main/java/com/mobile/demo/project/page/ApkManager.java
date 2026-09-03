package com.mobile.demo.project.page;

import com.mobile.demo.project.config.AppiumDriverConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApkManager {

    @Value("${app.package}")
    private String appPackage;

    @Autowired
    private AppiumDriverConfig driverConfig;

    public boolean isApkInstalled() {
        return driverConfig.getDriver().isAppInstalled(appPackage);
    }

    public void removeApk() {
        driverConfig.getDriver().removeApp(appPackage);
        log.info("Apk [{}] removed.", appPackage);
    }
}