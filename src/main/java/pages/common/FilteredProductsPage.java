package pages.common;

import models.Direction;
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
    private WebElement productResultsTotalNumber;
    @FindBy(css = "#js-active-search-filters .filter-block")
    private WebElement priceFilterActualValue;
    @FindBy(css = ".faceted-slider p")
    private WebElement priceFilterValues;
    @FindBy(css = ".ui-slider a:first-of-type")
    private WebElement leftSlider;
    @FindBy(css = ".ui-slider a:last-of-type")
    private WebElement rightSlider;

    public FilteredProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getResultsText() {
        return productResultsTotalNumber.getText();
    }

    public int getNumberFromProductResults() {
        return Integer.parseInt(productResultsTotalNumber.getText().replaceAll("\\D", ""));
    }

    public void moveLeftSliderToPrice(double price) {
        movePricesSlider(Direction.LEFT, price);
    }

    public void moveRightSliderToPrice(double price) {
        movePricesSlider(Direction.RIGHT, price);
    }

    private void movePricesSlider(Direction direction, double price) {
        double sliderValue = direction == Direction.LEFT ? getLeftSliderValue() : getRightSliderValue();
        WebElement slider = direction == Direction.LEFT ? leftSlider : rightSlider;
        if (price == sliderValue) {
            return;
        }

        if (price - sliderValue > 0) {
            sendKeyToSlider(slider, price - sliderValue, Keys.ARROW_RIGHT);
        } else {
            sendKeyToSlider(slider, sliderValue - price, Keys.ARROW_LEFT);
        }
        waitForFilteredPageToReload();
    }

    private void sendKeyToSlider(WebElement element, double count, Keys key) {
        for (int i = 0; i < count; i++) {
            waitForFilteredPageToReload();
            element.sendKeys(key);
        }
    }

    public void waitForFilteredPageToReload() {
        wait.until((ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner"))));
    }

    public double getLeftSliderValue() {
        return Double.parseDouble(StringUtils.substringBetween(priceFilterValues.getText(), "$", " -"));
    }

    public Double getRightSliderValue() {
        return Double.parseDouble(StringUtils.substringAfterLast(priceFilterValues.getText(), "$"));
    }


}
