package com.mavenit.selenium.training;

import com.mavenit.selenium.training.driver.DriverManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {


    private DriverManager driverManager = new DriverManager();
    private String url=System.getProperty("url");

    @Before
    public void setUp() {

        System.out.println("URL: "+url);
       // driverManager.runOnRemoteHost();
        driverManager.runOnLocalHost();
        driverManager.navigateTo(url);
        driverManager.maxBroser();
        driverManager.applyImplicit();
        driverManager.handleCookies();
    }

    @After
    public void tearDown(Scenario scenario) {


        if(scenario.isFailed()){
            driverManager.takeSceenShot(scenario);
        }

        driverManager.closeBrowser();
    }
}
