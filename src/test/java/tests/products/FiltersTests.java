package tests.products;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import testconfiguration.Pages;

import java.util.List;

public class FiltersTests extends Pages {

    @Test
    @Tag("filter")
    @DisplayName("Check price filter")
    @RepeatedTest(10)
    public void priceFilters() {
        Double lowestPrice = Double.parseDouble(System.getProperty("filtersLowestPrice"));
        Double highestPrice = Double.parseDouble(System.getProperty("filtersHighestPrice"));
        topMenuPage.openArtCategory();
        categoryPage.waitUntilCategoryHeaderTextLoads();
        filteredProductsPage.moveLeftSliderToPrice(lowestPrice);
        filteredProductsPage.moveRightSliderToPrice(highestPrice);
        filteredProductsPage.waitForFilteredPageToReload();
        productsListPage.waitForProductsToLoad();
        List<Double> productPrices = productsListPage.getProductPrices();
        Assertions.assertThat(productPrices).allMatch(x -> x >= lowestPrice && x <= highestPrice);
    }
}
