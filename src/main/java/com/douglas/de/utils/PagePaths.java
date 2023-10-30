package com.douglas.de.utils;

import com.douglas.de.pages.MainPage;
import com.douglas.de.pages.PerfumePage;
import com.google.common.collect.ImmutableMap;
import org.aeonbits.owner.ConfigFactory;

import java.util.*;

public class PagePaths {

    private static final Map<Class, String> PAGE_LINK_MAP =
        ImmutableMap.<Class, String>builder()
                    .put(MainPage.class, "de")
                    .put(PerfumePage.class, "de/c/parfum/01")
                    .build();

    public static String getLinkOfPage(Class pageClass) {
        return getLinkOfPage(pageClass, "");
    }

    public static String getLinkOfPage(Class pageClass, String... urlParam) {
        if (PAGE_LINK_MAP.containsKey(pageClass))
            return String.format(Config.config.baseUrl() + PAGE_LINK_MAP.get(pageClass), urlParam);
        throw new IllegalArgumentException("Unknown page object class: " + pageClass);
    }
}