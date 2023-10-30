package com.douglas.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.douglas.de.driver.WebDriverFactory;
import com.douglas.de.utils.CustomConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.*;

import static java.util.Objects.nonNull;

public class BaseTest {

    private static CustomConfig uiConfig = ConfigFactory.create(CustomConfig.class);

    @BeforeTest
    public void setUp() {
        Configuration.browser = WebDriverFactory.getDriverProviderClassName();
        Configuration.baseUrl = uiConfig.baseUrl();
        Configuration.timeout = uiConfig.browserTimeout();
        Configuration.browserSize = "1920x1080";
    }

    @AfterTest
    public void tearDown() {
        if (nonNull(WebDriverRunner.getWebDriver()))
            WebDriverRunner.getWebDriver().quit();
    }
}