package configuration.factory;

import configuration.models.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import models.BrowserOption;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class DriverFactory extends Browser {

    private WebDriver driver;
    public WebDriver getDriver(BrowserOption browser) {
        switch ( BrowserOption.valueOf(System.getProperty("browserName").toUpperCase()) ) {
            case FIREFOX -> driver = getFirefoxDriver();
            case EDGE -> driver = getEdgeDriver();
            case IE -> driver = getIEDriver();
            default -> driver = getChromeDriver();
        }
        return driver;
    }

    public WebDriver getDriver() {
        String browserName = System.getProperty("browserName");
        switch ( browserName ) {
            case "chrome" -> driver = getChromeDriver();
            case "firefox" -> driver = getFirefoxDriver();
            case "edge" -> driver = getEdgeDriver();
            default -> driver = getIEDriver();
        }
        return driver;
    }
    private WebDriver getIEDriver() {
        InternetExplorerOptions optionsDefault = new InternetExplorerOptions();
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver(optionsDefault);
        driver.get(System.getProperty("appUrl"));
        return driver;
    }

    private WebDriver getEdgeDriver() {
        EdgeOptions optionsEdge = new EdgeOptions();
        WebDriverManager.firefoxdriver().setup();
        optionsEdge.addArguments("start-maximized");
        driver = new EdgeDriver(optionsEdge);
        driver.get(System.getProperty("appUrl"));
        return driver;
    }

    private WebDriver getFirefoxDriver() {
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        optionsFirefox.addArguments("start-maximized");
        driver = new FirefoxDriver(optionsFirefox);
        driver.get(System.getProperty("appUrl"));
        return driver;
    }

    private WebDriver getChromeDriver() {
        ChromeOptions optionsChrome = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        optionsChrome.addArguments("start-maximized");
        driver = new ChromeDriver(optionsChrome);
        driver.get(System.getProperty("appUrl"));
        return driver;
    }
}

