package testconfiguration;

import configuration.factory.DriverFactory;
import configuration.properties.AllProperties;
import models.BrowserOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {
    private static final Logger logger = LoggerFactory.getLogger(TestBase.class);
    private static AllProperties allProperties;
    private static DriverFactory driverFactory;
    private static BrowserOption browserOption;
    public WebDriver driver;

    @BeforeAll
    static void setupDriver() {
        allProperties = AllProperties.getInstance();
        driverFactory = new DriverFactory();
        browserOption = BrowserOption.valueOf(System.getProperty("browserName").toUpperCase());
    }

    @BeforeEach
    void setupStart() {
        driver = driverFactory.getDriver(browserOption);
    }


    @AfterEach
    void tearDown() {
        driver.quit();
        logger.info("Webdriver closed");
    }
}
