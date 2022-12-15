package tests.products;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import testconfiguration.Pages;

import java.util.List;

import static java.lang.Double.parseDouble;
import static org.assertj.core.api.Assertions.assertThat;

public class FiltersTests extends Pages {

    @Tag("filter")
    @DisplayName("Check price filter")
    @RepeatedTest(10)
    public void priceFilters() {
        double lowestPrice = parseDouble(System.getProperty("filtersLowestPrice"));
        double highestPrice = parseDouble(System.getProperty("filtersHighestPrice"));

        topMenuPage.openArtCategory();

        filteredProductsPage.moveLeftSliderToPrice(lowestPrice);
        filteredProductsPage.moveRightSliderToPrice(highestPrice);

        List<Double> productPrices = productsListPage.getProductPrices();

        assertThat(productPrices).isNotEmpty();
        assertThat(productPrices).allMatch(x -> x >= lowestPrice && x <= highestPrice);
    }
}
