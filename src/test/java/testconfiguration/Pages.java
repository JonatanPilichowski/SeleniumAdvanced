package testconfiguration;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.interactions.Actions;
import pages.common.*;

public class Pages extends TestBase{
    public TopMenuPage topMenuPage;
    public ProductsListPage productsListPage;
    public CategoryPage categoryPage;
    public FilteredProductsPage filteredProductsPage;
    public SoftAssertions softly;

    public Actions action;

    @BeforeEach
    public void setPages(){
        topMenuPage = new TopMenuPage(driver);
        productsListPage = new ProductsListPage(driver);
        categoryPage = new CategoryPage(driver);
        filteredProductsPage = new FilteredProductsPage(driver);
        softly = new SoftAssertions();
        action = new Actions(driver);
    }
}
