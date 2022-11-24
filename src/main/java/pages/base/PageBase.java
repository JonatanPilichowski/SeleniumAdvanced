package pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class PageBase {

    private static final Logger logger = LoggerFactory.getLogger(PageBase.class);

    public PageBase(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        actions = new Actions(driver);
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebDriver driver;
    public Actions actions;
    public WebDriverWait wait;

    public String getNameOfItemFrom(WebElement element) {
        return element.findElement(By.cssSelector("a")).getAttribute("textContent");
    }
    public void click(WebElement element) {
        element.click();
    }

    public void sendKeys(WebElement element, String text) {
        logger.info("Typing: " + text);
        element.sendKeys(text);
    }
    public WebElement getRandomElement(List<WebElement> elements) {
        return elements.get(new Random().nextInt(elements.size()));
    }
    public int countOfElements(List<WebElement> listOfItems){
        return listOfItems.size();
    }

    public void sendKeysWithClear(WebElement element, String text) {
        logger.info("Clearing text: ");
        element.clear();

        if (element.getText().length() != 0) {
            element.sendKeys(Keys.ALT + "a" + Keys.DELETE);
        }
        sendKeys(element, text);
    }

    public int countElementsInListContainingText(List<WebElement> listOfElements, String text){
        int count=0;
        for(WebElement element: listOfElements){
            if(element.getText().contains(text)){
                count++;
            }
            else{
                logger.info("The product: " + element.getText() + " does not contain text: " + text);
            }
        }
        return count;
    }
}
