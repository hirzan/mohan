package com.mavenit.selenium.training.pages;

import com.mavenit.selenium.training.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDescriptionPage extends DriverManager {

    @FindBy(xpath = "//div[4]/div[2]/button/span/span")
    private WebElement trolleyBtn;

    @FindBy(xpath = "//a[contains(text(),'Go to Trolley')]")
    private WebElement goToTrolleyBtn;

    public void addToBasket() {
        waitUntilElementClickable(trolleyBtn).click();
    }

    public void goToTrolley() {
        waitUntilElementClickable(goToTrolleyBtn).click();
    }
}
