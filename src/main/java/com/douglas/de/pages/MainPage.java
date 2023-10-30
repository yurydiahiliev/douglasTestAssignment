package com.douglas.de.pages;

import com.codeborne.selenide.SelenideElement;
import com.douglas.de.pages.alerts.CookieAlert;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.douglas.de.utils.WaitElementTimeout.SMALL;
import static com.douglas.de.utils.WaitUtils.isPageObjectLoaded;

public class MainPage extends BasePage {

    public MainPage() {
        isPageObjectLoaded(Locators.PAGE_LOCATOR, SMALL, "Main page");
    }

    @Step
    public PerfumePage clickOnPerfumeNavBar() {
        Locators.PERFUME_NAV_ITEM.click();
        return new PerfumePage();
    }

    @Step
    public MainPage acceptAllCookies() {
        return new CookieAlert().clickOnAcceptAllCookies();
    }

    interface Locators {
        SelenideElement PAGE_LOCATOR = $(".content [data-testid='grid']");
        SelenideElement PERFUME_NAV_ITEM = $(".navigation-main-entry a[href='/de/c/parfum/01']");
    }
}
