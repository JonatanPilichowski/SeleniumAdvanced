package tests.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import testconfiguration.Pages;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTests extends Pages {

    @RepeatedTest(10)
    @Tag("search")
    @Tag("regression")
    @DisplayName("Standard search test (generic)")
    public void searchRandomItem() {
        int initialProductsActualCount = productsListPage.getNumberOfProducts();

        assertThat(initialProductsActualCount).isPositive();

        String randomProductText = productsListPage.getNameForRandomProduct();

        topMenuPage.focusOnSearch();
        topMenuPage.searchForProduct(randomProductText);

        List<String> allResultItems = productsListPage.getAllItemsName();

        assertThat(allResultItems).isEqualTo(Collections.singletonList(randomProductText));
    }

    @Tag("search")
    @Tag("regression")
    @DisplayName("Dropdown for search results test")
    @RepeatedTest(10)
    public void searchDropdownResults() {
        topMenuPage.focusOnSearch();
        topMenuPage.insertTextToSearch(System.getProperty("searchValue"));

        int numberOfResultsInDropdown = topMenuPage.countResultsInDropdown();
        List<String> searchDropdownTexts = topMenuPage.getSearchDropdownTexts();

        softly.assertThat(numberOfResultsInDropdown).isNotEqualTo(0);
        softly.assertThat(searchDropdownTexts).allMatch(x -> x.contains(System.getProperty("searchValue")));
        softly.assertAll();
    }
}
