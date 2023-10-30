package com.douglas.de.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.douglas.de.utils.WaitElementTimeout.MEDIUM;
import static com.douglas.de.utils.WaitUtils.waitUntilHidden;
import static java.lang.String.format;

public class PerfumePage extends BasePage {

    @Step
    public PerfumePage filterBy(PerfumeFilter perfumeFilter, String filterValue) {
        $("[class='row facet-list']")
            .scrollIntoView(false)
            .hover()
            .shouldBe(Condition.visible, Duration.ofMillis(MEDIUM.getValue()));
        $x(format(Locators.PRODUCT_FILTER_DROPDOWN_TEMPLATE, perfumeFilter.getFilterName())).scrollTo().click();
        $x(format(Locators.FILTER_ITEM_TEMPLATE, filterValue)).scrollTo().click();
        Locators.CLOSE_FILTER_DROP_DOWN_BUTTON.click(ClickOptions.usingJavaScript());
        waitUntilHidden(Locators.SCROLL_BAR_CONTAINER, MEDIUM);
        return this;
    }

    public ProductContentPage getProductContentPage() {
        return new ProductContentPage();
    }

    interface Locators {
        String PRODUCT_FILTER_DROPDOWN_TEMPLATE = "//div[@class='facet__title' and text()='%s']";
        String FILTER_ITEM_TEMPLATE = "//div[contains(text(), '%s')]/../..//div[@class='facet-option__checkbox']";
        SelenideElement CLOSE_FILTER_DROP_DOWN_BUTTON = $(".facet__menu-content .facet__close-button");
        SelenideElement SCROLL_BAR_CONTAINER = $(".rc-scrollbars-container");
    }

    public enum PerfumeFilter {
        PRODUCTART("Produktart"),
        MARKE("Marke"),
        FUR_WEN("Für Wen"),
        GESCHENK_FUR("Geschenk für"),
        DUFTNOTE("Duftnote"),
        HIGHLIGHTS("Highlights"),
        PRODUKTMERKMAL("Produktmerkmal"),
        PREIS("Preis");

        private String filterName;

        PerfumeFilter(String filterName) {
            this.filterName = filterName;
        }

        public String getFilterName() {
            return filterName;
        }
    }
}