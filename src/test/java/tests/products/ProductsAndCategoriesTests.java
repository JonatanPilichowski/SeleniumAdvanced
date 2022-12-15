package tests.products;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import testconfiguration.Pages;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductsAndCategoriesTests extends Pages {

    @Tag("categories")
    @DisplayName("Main categories opening")
    @RepeatedTest(10)
    public void openEachMainCategory() {
        List<String> categoriesInMenuNames = topMenuPage.getCategoriesNames();
        assertThat(categoriesInMenuNames).isNotEmpty();

        for (String category : categoriesInMenuNames) {
            topMenuPage.openCategoryByName(category);

            String categoryHeader = categoryPage.getCategoryHeaderName();

            softly.assertThat(categoryHeader).isEqualTo(category);

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
