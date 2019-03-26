package com.mavenit.selenium.training.driver;

import cucumber.api.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    public static WebDriver driver;
    private String browser = System.getProperty("instance");


    public DriverManager() {
        PageFactory.initElements(driver, this);
    }

    public void runOnLocalHost() {
        System.out.println("Running instance is : " + browser);

        switch (browser) {
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            default:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
        }
    }

    public void runOnRemoteHost() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.1.120:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public void maxBroser() {
        driver.manage().window().maximize();
    }

    public void handleCookies() {
        driver.findElement(By.linkText("GOT IT")).click();
    }


    public void navigateTo(String url) {
        driver.get(url);
    }

    public void closeBrowser() {
        driver.quit();
    }

    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void applyImplicit() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public WebElement waitUntilElementClickable(WebElement element) {
        return new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(element));
    }

    public Boolean waitUntilElementInvisible(By by) {
        return new WebDriverWait(driver, 15).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }


    public void takeSceenShot(Scenario scenario) {
        byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenShot, "image/png");
    }
}
