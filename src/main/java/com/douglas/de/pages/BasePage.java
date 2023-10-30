package com.douglas.de.pages;

import com.codeborne.selenide.Selenide;
import com.douglas.de.utils.CustomConfig;
import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.page;
import static com.douglas.de.utils.PagePaths.getLinkOfPage;

public class BasePage {

    private static CustomConfig uiConfig = ConfigFactory.create(CustomConfig.class);
    private static final Logger log = LoggerFactory.getLogger(BasePage.class);

    public static <T extends BasePage> T openPage(Class<T> pageObjectClass) {
        open(getLinkOfPage(pageObjectClass));
        log.info("Opening page: {}", pageObjectClass.getName());
        return page(pageObjectClass);
    }

    public static <T extends BasePage> T at(Class<T> pageObjectClass) {
        log.info("At page: {}", pageObjectClass.getName());
        return page(pageObjectClass);
    }

    private static void open(String url) {
        Selenide.open(url);
    }
}