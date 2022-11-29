package com.hsf;

import com.hsf.conf.ManagedWebDriver;
import com.hsf.conf.WebDriverIterator;
import io.cucumber.testng.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

@CucumberOptions(
        glue = "com.hsf.steps",
        plugin = {
                "pretty",
                "html:reports/tests/cucumber/cucumber-pretty.html",
                "testng:reports/tests/cucumber/testng/cucumber.xml",
                "json:reports/tests/cucumber/json/cucumberTestReport.json"
        }
)
public class RunWebDriverCucumberTests {

        private Logger logger = LoggerFactory.getLogger(this.getClass());
        private TestNGCucumberRunner testNGCucumberRunner;
        private static final ThreadLocal<ManagedWebDriver> threadLocalWebDriver = new ThreadLocal<>();

        @BeforeClass(alwaysRun = true)
        public void setUpClass() {
                testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        }
        public static WebDriver getWebDriver() {

                return WebDriverManager.getInstance("chrome").create();
        }

        public synchronized static void setThreadLocalWebDriver(ManagedWebDriver managedWebDriver) {
                threadLocalWebDriver.set(managedWebDriver);
        }

        public synchronized static ManagedWebDriver getManagedWebDriver() {
                return threadLocalWebDriver.get();
        }

        @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "scenarios")
        public void feature(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper, ManagedWebDriver managedWebDriver) {

                managedWebDriver.setTestName(pickleWrapper.getPickle().getName());
                setThreadLocalWebDriver(managedWebDriver);
                testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
        }

        @DataProvider(name = "scenarios", parallel = true)
        public Iterator<Object[]> scenarios() {
                Object[][] scenarios = testNGCucumberRunner.provideScenarios();
                logger.info("scenarios {}", scenarios);
                return new WebDriverIterator(scenarios);
        }

        @AfterClass(alwaysRun = true)
        public void tearDownClass() {
                if (testNGCucumberRunner == null) {
                        return;
                }
                testNGCucumberRunner.finish();
        }


}
