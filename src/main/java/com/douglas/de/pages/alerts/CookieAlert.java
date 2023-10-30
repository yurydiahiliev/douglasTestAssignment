package com.douglas.de.pages.alerts;

import com.codeborne.selenide.SelenideElement;
import com.douglas.de.pages.BasePage;
import com.douglas.de.pages.MainPage;

import static com.codeborne.selenide.Selenide.$;
import static com.douglas.de.utils.WaitElementTimeout.SMALL;
import static com.douglas.de.utils.WaitUtils.waitUntilDisplayed;
import static com.douglas.de.utils.WaitUtils.waitUntilHidden;

public class CookieAlert extends BasePage {

    public MainPage clickOnAcceptAllCookies() {
        if (waitUntilDisplayed(Locators.ACCEPT_ALL_BUTTON, SMALL)) {
            Locators.ACCEPT_ALL_BUTTON.click();
            waitUntilHidden(Locators.PAGE_LOCATOR, SMALL);
        }
        return new MainPage();
    }

    interface Locators {
        SelenideElement PAGE_LOCATOR = $(".uc-banner-modal,[role=dialog]");
        SelenideElement ACCEPT_ALL_BUTTON = $(".uc-list-button__accept-all");
    }
}