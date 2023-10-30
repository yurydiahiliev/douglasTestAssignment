package com.douglas.de.driver;

import static java.util.Objects.isNull;

public class WebDriverFactory {

    public static String getDriverProviderClassName() {
        String providerClassName;
        String browserProperty = System.getProperty("browser");
        String browser = isNull(browserProperty) ? "chrome" : browserProperty.toLowerCase();

        if ("firefox".equals(browser)) {
            providerClassName = FirefoxDriverProvider.class.getName();
        } else {
            providerClassName = ChromeDriverProvider.class.getName();
        }

        return providerClassName;
    }
}