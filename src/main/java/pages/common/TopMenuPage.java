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
    WebElement searchInput;
    @FindBy(css = "button[type='submit']")
    WebElement searchBtn;
    @FindBy(css = "#ui-id-1 .product")
    List<WebElement> searchDropdownList;
    @FindBy(css = "#top-menu > li")
    List<WebElement> categories;

    @FindBy(css = "#category-9")
    WebElement artCategory;

    public TopMenuPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getCategories() {
        return categories;
    }

    public void openCategoryByName(String categoryName) {
        for (WebElement category : getCategories()) {
            if (category.getText().trim().equals(categoryName)) {
                click(category);
                break;
            }
        }
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


    public void clickOnSearchInput() {
        click(searchInput);
        logger.info("Clicked on search input field");
    }

    public void insertTextToInput(String text) {
        sendKeysWithClear(searchInput, text);
        logger.info("Text: " + text + " was inserted to the search");
    }

    public void insertTextToInputAndSearch(String text) {
        insertTextToInput(text);
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

    public int countDropdownTextsContainingSearchValue() {
        return countElementsInListContainingText(searchDropdownList, System.getProperty("searchValue"));
    }

}
