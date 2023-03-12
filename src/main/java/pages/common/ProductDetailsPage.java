package pages.common;

import models.Cart;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.PageBase;

import java.math.BigDecimal;
import java.util.Random;

public class ProductDetailsPage extends PageBase {
    @FindBy(css = ".h1")
    private WebElement productName;
    @FindBy(css = "#quantity_wanted")
    private WebElement quantity;
    @FindBy(css = ".add-to-cart")
    private WebElement addToCartBtn;
    @FindBy(css = ".current-price span")
    private WebElement productPrice;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public BigDecimal getProductPrice() {
        return getPrice(productPrice);
    }

    public void setRandomQuantity(int min, int max) {
        Random rand = new Random();
        String randomQuantity = String.valueOf(rand.nextInt(max - min) + min);
        sendKeysWithClear(quantity, randomQuantity);
    }

    public String getQuantity() {
        return quantity.getAttribute("value");
    }

    public void setQuantity(String count) {
        sendKeysWithClear(quantity, count);
    }

    public void addProductToCart(Cart cart) {
        cart.addProduct(new Product(productName.getText(), getProductPrice(), Integer.parseInt(getQuantity())));
        click(addToCartBtn);
    }
}
