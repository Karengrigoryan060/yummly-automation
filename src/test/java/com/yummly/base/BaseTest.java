package com.yummly.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import static com.yummly.config.DriverFactory.initDriver;
import static com.yummly.config.DriverFactory.stopDriver;

public abstract class BaseTest {

    @BeforeMethod
    @Parameters({"platform"})
    public void methodSetup (String platform) {
        initDriver(platform);
    }

    @AfterMethod
    public void methodTearDown () {
        stopDriver();
    }

}
