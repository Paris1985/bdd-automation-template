package com.hsf.test;

import com.hsf.RunWebDriverCucumberTests;
import com.hsf.conf.ManagedWebDriver;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {
    @Before
    public void init() {
        ManagedWebDriver managedWebDriver = new ManagedWebDriver("test", new JSONObject());
        RunWebDriverCucumberTests.setThreadLocalWebDriver(managedWebDriver);
    }
    @After
    public void tearDown() {
       // RemoteCucumberTestRunner.getWebDriver().quit();
    }
}
