package com.douglas.de.driver;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Nonnull;
import java.util.*;

public class ChromeDriverProvider implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        Map<String, Object> prefs = new HashMap();
        prefs.put("profile.block_third_party_cookies", true);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("prefs", prefs);

        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }
}