package com.mavenit.selenium.training.pages;

import com.mavenit.selenium.training.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ResultsPage extends DriverManager {

    @FindBy(css = ".search-title__term")
    private WebElement headerText;

    @FindBy(css = ".ac-product-card__name")
    private List<WebElement> productNames;

    @FindBy(css = ".ac-facet__label")
    private List<WebElement> filtersBy;

    @FindBy(css = ".ac-star-rating")
    private List<WebElement> reviewImageOnProducts;

    @FindBy(css = ".ac-product-price__amount")
    private List<WebElement> pricesOnProducts;

    public static String selectedProduct;

    public String getProductHeader() {
        return headerText.getText();
    }

    public void selectAnyProduct() {
        int totalProducts = productNames.size();
        if(totalProducts==0){
            throw new RuntimeException("No products found.");
        }
        Random random = new Random();
        int randomNumber = random.nextInt(totalProducts);
        WebElement selectedWebElement = productNames.get(randomNumber);
        selectedProduct = selectedWebElement.getText();
        selectedWebElement.click();
    }


    public void selectFilterBy(String filterChoice) {
        for (WebElement filterElement : filtersBy) {
            if (filterElement.getText().equalsIgnoreCase(filterChoice)) {
               waitUntilElementClickable(filterElement).click();
                break;
            }
        }
//        sleep(9000);
        waitUntilElementInvisible(By.cssSelector(".icon--loading"));

    }

    public List<Double> getAllProductsPrices() {
        List<Double> collectedPriceList = new ArrayList<>();

        for (WebElement filterWebelement : pricesOnProducts) {
            double indiPrice = Double.parseDouble(filterWebelement.getText().replace("£", ""));
            collectedPriceList.add(indiPrice);
        }
        return collectedPriceList;
    }


    public List<Double> getAllRatingOnProducts() {
        List<Double> collectedRatingList = new ArrayList<>();
        for (WebElement filterWebelemt : reviewImageOnProducts) {
            String ratingValueInString = filterWebelemt.getAttribute("data-star-rating");
            double ratingValueInDouble = Double.parseDouble(ratingValueInString);
            collectedRatingList.add(ratingValueInDouble);
        }
        return collectedRatingList;
    }
}
