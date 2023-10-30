package com.douglas.de.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.*;

import static com.codeborne.selenide.Selenide.$$;
import static com.douglas.de.utils.CommonElement.getText;

public class ProductContentPage extends BasePage {

    @Step
    public List<ProductContent> getProductsInContent() {
        Locators.PRODUCT_GRIDS.shouldHave(CollectionCondition.sizeGreaterThan(0));
        return Locators.PRODUCT_TILES
            .shouldHave(CollectionCondition.sizeGreaterThan(0))
            .asDynamicIterable()
            .stream()
            .map(ProductContent::new)
            .toList();
    }

    interface Locators {
        ElementsCollection PRODUCT_GRIDS = $$(".product-grid__listing-content .product-grid");
        ElementsCollection PRODUCT_TILES = $$(".product-tile");
    }

    public static class ProductContent {

        private SelenideElement productTile;
        private SelenideElement productInfo;

        public ProductContent(SelenideElement productTile) {
            this.productTile = productTile;
            this.productInfo = this.productTile.$(Locators.PRODUCT_INFO);
        }

        public String getTopBrand() {
            return getText(this.productInfo.find(".top-brand"));
        }

        public String getBrandLine() {
            return getText(this.productInfo.find(".brand-line"));
        }

        public String getName() {
            return getText(this.productInfo.find(".name"));
        }

        public String getCategory() {
            return getText(this.productInfo.find(".category"));
        }

        public String getPrice() {
            return getText(this.productInfo.find(".price"));
        }

        public boolean isSale() {
            return this.productTile.find(".eyecatcher--discount").isDisplayed();
        }

        public boolean isNew() {
            return this.productTile.find(".eyecatcher--new").isDisplayed();
        }

        interface Locators {
            String PRODUCT_INFO = ".product-info";
        }
    }
}