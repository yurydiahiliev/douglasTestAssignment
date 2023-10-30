package com.douglas.tests;

import com.douglas.de.pages.MainPage;
import com.douglas.de.pages.PerfumePage;
import com.douglas.de.pages.ProductContentPage;
import com.douglas.de.pages.pojo.ProductInContent;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Function;

import static com.douglas.de.pages.BasePage.openPage;
import static com.douglas.de.pages.PerfumePage.PerfumeFilter.*;
import static com.douglas.de.utils.CSVUtils.convertCsvToObj;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isIn;

public class FilterProductsTests extends BaseTest {

    private PerfumePage perfumePage;

    @BeforeMethod
    public void openMainPage() {
        perfumePage = openPage(MainPage.class).acceptAllCookies().clickOnPerfumeNavBar();
    }

    @Test(dataProvider = "filterDataProvider")
    public void checkFilterByCriteriaProducts(String filterValue,
                                          PerfumePage.PerfumeFilter perfumeFilter,
                                          String value,
                                          String fileName) {
        List<ProductInContent> productInContentList =
            perfumePage
                .filterBy(HIGHLIGHTS, filterValue)
                .filterBy(perfumeFilter, value)
                .getProductContentPage()
                .getProductsInContent().stream()
                .map(mapToProductContent).toList();

        List<ProductInContent> expectedProductInContentList = convertCsvToObj(fileName);
        assertThat(expectedProductInContentList.get(0), isIn(productInContentList));
    }

    @DataProvider
    public Object[][] filterDataProvider() {
        return new Object[][] {
            {"Sale", PRODUCTART, "Eau de Parfum", "sale_product_type.csv"},
            {"Sale", MARKE, "Abercrombie & Fitch", "sale_brand.csv"},
            {"Sale", FUR_WEN, "Männlich", "sale_for_whom.csv"},
            {"NEU", PRODUCTART, "Duftset", "new_product_type.csv"},
            {"NEU", FUR_WEN, "Unisex", "new_for_whom.csv"},
            {"Limitiert", PRODUCTART, "Eau de Toilette", "limited_product_type.csv"},
            {"Limitiert", MARKE, "Aigner", "limited_brand.csv"},
            {"Limitiert", GESCHENK_FUR, "Nikolaus", "limited_present_for.csv"},
            {"Limitiert", FUR_WEN, "Männlich", "limited_for_whom.csv"}
        };
    }

    private Function<ProductContentPage.ProductContent, ProductInContent> mapToProductContent = content -> {
        ProductInContent productInContent = new ProductInContent();
        productInContent.setTopBrand(content.getTopBrand());
        productInContent.setBrandLine(content.getBrandLine());
        productInContent.setName(content.getName());
        productInContent.setCategory(content.getCategory());
        productInContent.setPriceInfo(content.getPrice());
        return productInContent;
    };
}