package tests.basket;

import models.Cart;
import models.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import testconfiguration.Pages;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

public class BasketTests extends Pages {

    @RepeatedTest(10)
    @DisplayName("Adding product to the basket")
    @Tag("basket")
    public void basketPopup(){
        String productQuantity = System.getProperty("basketProductsAmount");
        String productName = System.getProperty("basketTestProduct");
        Cart expectedCart = new Cart();

        topMenuPage.openArtCategory();

        productsListPage.openProductNamed(productName);

        productDetailsPage.setQuantity(productQuantity);

        assertThat("Check if quantity was applied",productDetailsPage.getQuantity().equals(productQuantity));

        productDetailsPage.addProductToCart(expectedCart);
        Product expectedProduct = expectedCart.getProduct(productName);

        softly.assertThat(cartContentPage.getProductName()).isEqualTo(productName);
        softly.assertThat(cartContentPage.getProductPrice()).isEqualTo(expectedProduct.getPrice());
        softly.assertThat(cartContentPage.getSubtotalPrice()).isEqualTo(expectedCart.getTotalOrderCost());
        softly.assertThat(cartContentPage.getShippingPrice().add(expectedCart.getTotalOrderCost())).isEqualTo(cartContentPage.getTotalPrice());
        softly.assertThat(cartContentPage.getCartAmountInfo()).isEqualTo(System.getProperty("basketText1") + expectedProduct.getCount() + System.getProperty("basketText2"));

        softly.assertAll();
    }


    @RepeatedTest(1)
    @DisplayName("Generic basket calculations")
    @Tag("basket")
    public void basketCalculations(){

        for (int i = 0; i < 5; i++) {
            Cart expectedCart = new Cart();
            productsListPage.openRandomProduct();
            topMenuPage.openHomePage();
            productDetailsPage.setRandomQuantity(1,5);
            productDetailsPage.addProductToCart(expectedCart);
            cartContentPage.continueShopping();
            topMenuPage.openHomePage();
        }
        topMenuPage.openCart();


        //todo: zaladowac z koszyka Webelementy dla: NazwaProduktu, cenaProduktu, QuantityProduktu, sumaŁącznaProduktu(cena*quantity), cena łączna zamówienia
        //todo: zaladowac wszystkie produkty do Listy forEach product
        //todo: porównać z expectedCart w jednej asercji?

    }
    public static int sum(int[] numbers){
        if(numbers.length <2){
            return 0;
        }
        //.filter(product -> product.getText().equals(productName)).findFirst().ifPresent(WebElement::click)
       // return Arrays.stream(numbers).filter(number -> Arrays.stream(numbers).findAny().stream().max())
        return 0;
    }
}
