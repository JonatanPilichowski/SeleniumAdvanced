package pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.PageBase;

import java.util.ArrayList;
import java.util.List;

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

    public String getNameForRandomProduct() {
        return getNameOfItemFrom(getRandomItemFromList());
    }

    public void openRandomItem() {
        WebElement item = getRandomElement(productsNamesList);
        logger.info("Clicking on: " + item.getText());
        click(item);
    }

    public int getNumberOfProducts() {
        return getAllItems().size();
    }

    public String getFirstItemName() {
        return getNameOfItemFrom(productsNamesList.get(0));
    }

    public List<String> getAllItemsName() {
        List<String> allItemsName = new ArrayList<>();
        for (WebElement item : productsNamesList) {
            allItemsName.add(item.getText());
        }
        return allItemsName;
    }

    public void waitForProductsToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#products")));
    }

    public List<WebElement> getProductsPricesList() {
        return productsPricesList;
    }

    public List<Double> getProductPrices() {
        List<Double> productPrices = new ArrayList<>();
        List<WebElement> productsPricesList = getProductsPricesList();
        for (WebElement price : productsPricesList) {
            productPrices.add(Double.parseDouble(price.getText().replace("$", "")));
        }
        return productPrices;
    }

}
