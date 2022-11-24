package pages.common;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.PageBase;

public class FilteredProductsPage extends PageBase {

    @FindBy(css = ".total-products")
    WebElement productResultsTotalNumber;
    @FindBy(css = "#js-active-search-filters .filter-block")
    WebElement priceFilterActualValue;
    @FindBy(css = ".faceted-slider p")
    WebElement priceFilterValues;
    @FindBy(css = ".ui-slider a:first-of-type")
    WebElement leftPricesSlider;
    @FindBy(css = ".ui-slider a:last-of-type")
    WebElement rightPricesSlider;

    public FilteredProductsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getLeftPricesSlider() {
        return leftPricesSlider;
    }

    public WebElement getRightPricesSlider() {
        return rightPricesSlider;
    }

    public String getResultsText() {
        return productResultsTotalNumber.getText();
    }

    public int getNumberFromProductResults() {
        return Integer.parseInt(productResultsTotalNumber.getText().replaceAll("\\D", ""));
    }

    public void moveLeftSliderToPrice(double price) {
        double sliderValue = getLeftSliderValue();
        WebElement leftSliderPrices = getLeftPricesSlider();
        if ((price - sliderValue) >= 0) {
            for (int i = 0; i < (price - sliderValue); i++) {
                waitForSliderToBeAvailable();
                leftSliderPrices.sendKeys(Keys.ARROW_RIGHT);
            }
        } else {
            for (int i = 0; i < (sliderValue - price); i++) {
                waitForSliderToBeAvailable();
                leftSliderPrices.sendKeys(Keys.ARROW_LEFT);
            }
        }
    }

    public void moveRightSliderToPrice(double price) {
        double sliderValue = getRightSliderValue();
        WebElement rightPricesSlider = getRightPricesSlider();
        if ((price - sliderValue) >= 0) {
            for (int i = 0; i < (price - sliderValue); i++) {
                waitForSliderToBeAvailable();
                rightPricesSlider.sendKeys(Keys.ARROW_RIGHT);
            }
        } else {
            for (int i = 0; i < (sliderValue - price); i++) {
                waitForSliderToBeAvailable();
                rightPricesSlider.sendKeys(Keys.ARROW_LEFT);
            }
        }
    }

    public String getActivePriceFilterText() {
        return priceFilterActualValue.getText();
    }

    public void waitForPageToReload() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".filter-block")));
    }

    public void waitForSliderToBeAvailable() {
        wait.until((ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner"))));
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-slider a:last-of-type"))));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ui-slider a:first-of-type")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ui-slider a:last-of-type")));
    }

    public void waitForFilteredPageToReload() {
        wait.until((ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner"))));
    }

    public String getPriceFilterText() {
        return priceFilterValues.getText();
    }

    public double getLeftSliderValue() {
        String priceFilterText = getPriceFilterText();
        return Double.parseDouble(StringUtils.substringBetween(priceFilterText, "$", " -"));
    }

    public Double getRightSliderValue() {
        String priceFilterText = getPriceFilterText();
        return Double.parseDouble(StringUtils.substringAfterLast(priceFilterText, "$"));
    }


}
