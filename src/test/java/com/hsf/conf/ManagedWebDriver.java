package com.hsf.conf;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;

public class ManagedWebDriver {
    private String testName;
    private final WebDriverFactory webDriverFactory;
    private final JSONObject platform;
    private WebDriver webDriver;

    public ManagedWebDriver(String testMethodName, JSONObject platform) {
        this.testName = testMethodName;
        this.platform = platform;
        this.webDriverFactory = WebDriverFactory.getInstance();
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public WebDriver getWebDriverForPlatform() {
        if (this.webDriver == null) {
            this.webDriver = this.webDriverFactory.createWebDriverForPlatform(this.platform, this.testName);
        }
        return this.webDriver;
    }

}
