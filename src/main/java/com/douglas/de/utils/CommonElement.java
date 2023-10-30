package com.douglas.de.utils;

import com.codeborne.selenide.SelenideElement;

public class CommonElement {

    public static String getText(SelenideElement element) {
        return element.isDisplayed() ? element.getText() : "";
    }
}