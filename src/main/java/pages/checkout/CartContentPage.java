package pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.PageBase;

import java.math.BigDecimal;

public class CartContentPage extends PageBase {
    public CartContentPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = ".product-name")
    private WebElement productName;

    @FindBy(css = ".modal-body .product-price")
    private WebElement productPrice;

    @FindBy(css = ".subtotal")
    private WebElement subtotalPrice;

    @FindBy(css = ".shipping")
    private WebElement shipping;

    @FindBy(css = ".product-total .value")
    private WebElement totalPrice;

    @FindBy(css = ".modal .cart-products-count")
    private WebElement cartAmountInfo;

    @FindBy(css = ".btn-secondary")
    private WebElement continueShoppingButton;

    public void continueShopping(){
        click(continueShoppingButton);
    }
    public String getCartAmountInfo() {
        return cartAmountInfo.getText();
    }

    public String getProductName(){
        wait.until(ExpectedConditions.visibilityOf(productName));
        return productName.getText();
    }

    public BigDecimal getProductPrice(){
        wait.until(ExpectedConditions.visibilityOf(productPrice));
        return getPrice(productPrice);
    }

    public BigDecimal getSubtotalPrice(){
        wait.until(ExpectedConditions.visibilityOf(subtotalPrice));
        return getPrice(subtotalPrice);
    }

    public BigDecimal getShippingPrice(){
        wait.until(ExpectedConditions.visibilityOf(shipping));
        return getPrice(shipping);
    }

    public BigDecimal getTotalPrice(){
        wait.until(ExpectedConditions.visibilityOf(totalPrice));
        return  getPrice(totalPrice);
    }
}
