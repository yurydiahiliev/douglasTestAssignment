package com.douglas.de.utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.sleep;

public class WaitUtils {
    private static final Logger log = LoggerFactory.getLogger(WaitUtils.class);;
    private static long step = 300;

    public static boolean isPageObjectLoaded(SelenideElement element, WaitElementTimeout timeout, String assertionText) {
        int counter = 0;
        while (!element.isDisplayed() && counter <= timeout.getValue()) {
            sleep(step);
            counter += step;
            if (counter % 1000 == 0) {
                log.info("Loading page (element) '" + assertionText + "', counting: " + counter + " ms...");
            }
        }
        if (counter <= timeout.getValue()) {
            log.info("Page (element) '" + assertionText + "' was loaded!");
            return true;
        } else {
            throw new AssertionError("'" + assertionText + "' page (element) was not loaded or opened after " + timeout + " milliseconds!");
        }
    }

    public static boolean waitUntilDisplayed(SelenideElement element, WaitElementTimeout timeout) {
        int counter = 0;
        while (!element.isDisplayed() && counter <= timeout.getValue()) {
            sleep(step);
            counter += step;
        }
        return counter <= timeout.getValue();
    }

    public static boolean waitUntilHidden(WebElement element, WaitElementTimeout timeout) {
        int counter = 0;
        while (element.isDisplayed() && counter <= timeout.getValue()) {
            sleep(step);
            counter += step;
        }
        return counter <= timeout.getValue();
    }
}