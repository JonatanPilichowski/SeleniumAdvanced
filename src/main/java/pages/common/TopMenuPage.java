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

public class TopMenuPage extends PageBase {

    private static final Logger logger = LoggerFactory.getLogger(TopMenuPage.class);
    @FindBy(css = "#search_widget [type='text']")
    private WebElement searchInput;
    @FindBy(css = "button[type='submit']")
    private WebElement searchBtn;
    @FindBy(css = "#ui-id-1 .product")
    private List<WebElement> searchDropdownList;
    @FindBy(css = "#top-menu > li")
    private List<WebElement> categories;
    @FindBy(css = "#category-9")
    private WebElement artCategory;
    @FindBy(css = ".logo")
    private WebElement myStore;

    @FindBy(css = "#_desktop_cart .shopping-cart")
    private WebElement cartIcon;

    public void openCart(){click(cartIcon);}
    public void openHomePage(){click(myStore);}

    public TopMenuPage(WebDriver driver) {
        super(driver);
    }

    public void openCategoryByName(String categoryName) {
        categories.stream().filter(category -> category.getText().trim().equals(categoryName)).findFirst().ifPresent(this::click);
    }

    public void openArtCategory() {
        click(artCategory);
    }

    public List<String> getCategoriesNames() {
        List<String> categoriesNames = new ArrayList<>();
        for (WebElement category : categories) {
            categoriesNames.add(category.getText().trim());
        }
        return categoriesNames;
    }

    public void focusOnSearch() {
        click(searchInput);
        logger.info("Clicked on search input field");
    }

    public void insertTextToSearch(String text) {
        sendKeysWithClear(searchInput, text);
        logger.info("Text: " + text + " was inserted to the search");
        waitUntilDropdownSearchLoads();
    }

    public void searchForProduct(String text) {
        insertTextToSearch(text);
        click(searchBtn);
        logger.info("Searching, should move to search results");
    }

    public int countResultsInDropdown() {
        return getSearchDropdownTexts().size();
    }

    public void waitUntilDropdownSearchLoads() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ui-id-1")));
    }

    public List<String> getSearchDropdownTexts() {
        List<String> searchDropdownTexts = new ArrayList<>();
        for (WebElement element : searchDropdownList) {
            searchDropdownTexts.add(element.getText());
        }
        return searchDropdownTexts;
    }
}
