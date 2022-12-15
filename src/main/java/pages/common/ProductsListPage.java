package pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.PageBase;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsListPage extends PageBase {

    private static final Logger logger = LoggerFactory.getLogger(ProductsListPage.class);
    @FindBy(css = "[itemprop='item']")
    List<WebElement> productsList;
    @FindBy(css = ".product-title")
    List<WebElement> productsNamesList;
    @FindBy(css = "div .price")
    List<WebElement> productsPricesList;

    public ProductsListPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getAllItems() {
        return productsNamesList;
    }

    public WebElement getRandomItemFromList() {
        return getRandomElement(productsNamesList);
    }

    public String getNameOfItemFrom(WebElement element) {
        return element.findElement(By.cssSelector("a")).getAttribute("textContent");
    }

    public String getNameForRandomProduct() {
        return getNameOfItemFrom(getRandomItemFromList());
    }

    public int getNumberOfProducts() {
        return getAllItems().size();
    }


    public List<String> getAllItemsName() {
        return productsNamesList.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void waitForProductsToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#products")));
    }

    public List<WebElement> getProductsPricesList() {
        return productsPricesList;
    }

    public List<Double> getProductPrices() {
        waitForProductsToLoad();
        List<WebElement> productsPricesList = getProductsPricesList();
        return productsPricesList.stream().map(this::getPrice).collect(Collectors.toList());
    }

}
