package com.douglas.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.douglas.de.driver.WebDriverFactory;
import com.douglas.de.utils.Config;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static java.util.Objects.nonNull;

public class BaseTest {

    @BeforeTest
    public void setUp() {
        Configuration.browser = WebDriverFactory.getDriverProviderClassName();
        Configuration.baseUrl = Config.config.baseUrl();
        Configuration.timeout = Config.config.browserTimeout();
        Configuration.browserSize = "1920x1080";
    }

    @AfterTest
    public void tearDown() {
        if (nonNull(WebDriverRunner.getWebDriver()))
            WebDriverRunner.getWebDriver().quit();
    }
}