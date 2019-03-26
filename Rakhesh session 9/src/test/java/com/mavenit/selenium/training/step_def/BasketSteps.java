package com.mavenit.selenium.training.step_def;

import com.mavenit.selenium.training.pages.BasketPage;
import com.mavenit.selenium.training.pages.ProductDescriptionPage;
import com.mavenit.selenium.training.pages.ResultsPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class BasketSteps {

    private ResultsPage resultsPage = new ResultsPage();
    private ProductDescriptionPage productDescriptionPage = new ProductDescriptionPage();
    private BasketPage basketPage = new BasketPage();

    @And("^I select any product$")
    public void addProduct() {
        resultsPage.selectAnyProduct();
    }

    @And("^I add the product to the basket$")
    public void iAddTheProductToTheBasket() {
        productDescriptionPage.addToBasket();
        productDescriptionPage.goToTrolley();
    }

    @Then("^the product should be in the basket$")
    public void theProductShouldBeInTheBasket() {
        List<String> productList = basketPage.getProductsInBaskets();

        assertThat(productList,hasItem(ResultsPage.selectedProduct));

    }
}
