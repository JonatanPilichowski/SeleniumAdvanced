package pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.PageBase;

public class CategoryPage extends PageBase {

    @FindBy(css = ".h1")
    WebElement categoryHeader;

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public String getCategoryHeaderName() {
        waitUntilCategoryHeaderTextLoads();
        return categoryHeader.getText();
    }

    public void waitUntilCategoryHeaderTextLoads() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".h1")));
    }


    public void waitUntilCategoryLoads() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#js-product-list-header")));
    }

}
