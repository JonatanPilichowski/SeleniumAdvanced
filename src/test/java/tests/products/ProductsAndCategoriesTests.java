package tests.products;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import testconfiguration.Pages;

import java.util.List;

public class ProductsAndCategoriesTests extends Pages {

    @Test
    @Tag("categories")
    @DisplayName("Main categories opening")
    @RepeatedTest(10)
    public void openEachMainCategory() {
        List<String> categoriesInMenuNames = topMenuPage.getCategoriesNames();
        for (String category : categoriesInMenuNames) {
            topMenuPage.openCategoryByName(category);
            String categoryHeaderName = categoryPage.getCategoryHeaderName();
            softly.assertThat(categoryHeaderName).isEqualTo(category);
            int productsCount = productsListPage.getNumberOfProducts();
            int resultsTextCount = filteredProductsPage.getNumberFromProductResults();
            softly.assertThat(resultsTextCount).isEqualTo(productsCount);
            String resultsText = filteredProductsPage.getResultsText();
            String expectedResultText = System.getProperty("filteredResultsText1") + resultsTextCount + System.getProperty("filteredResultsText2");
            softly.assertThat(resultsText).isEqualTo(expectedResultText);
        }
        softly.assertAll();
    }
}
