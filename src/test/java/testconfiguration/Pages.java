package testconfiguration;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.interactions.Actions;
import pages.checkout.CartContentPage;
import pages.checkout.CheckoutPage;
import pages.common.*;

public class Pages extends TestBase {
    public TopMenuPage topMenuPage;
    public ProductsListPage productsListPage;
    public CategoryPage categoryPage;
    public FilteredProductsPage filteredProductsPage;
    public ProductDetailsPage productDetailsPage;
    public CartContentPage cartContentPage;
    public CheckoutPage checkoutPage;
    public SoftAssertions softly;
    public Actions action;

    @BeforeEach
    public void setPages() {
        topMenuPage = new TopMenuPage(driver);
        productsListPage = new ProductsListPage(driver);
        categoryPage = new CategoryPage(driver);
        filteredProductsPage = new FilteredProductsPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        cartContentPage = new CartContentPage(driver);
        checkoutPage = new CheckoutPage(driver);
        softly = new SoftAssertions();
        action = new Actions(driver);
    }
}
