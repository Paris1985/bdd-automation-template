package com.hsf.steps;

import com.hsf.RunWebDriverCucumberTests;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class BaseSteps {
    protected WebDriver driver;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public BaseSteps() {
        driver = RunWebDriverCucumberTests.getManagedWebDriver().getWebDriverForPlatform();
    }
}
