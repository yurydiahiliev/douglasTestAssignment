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
import static com.douglas.de.utils.CSVUtils.convertToCSV;
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
                                          int limit,
                                          PerfumePage.PerfumeFilter perfumeFilter,
                                          String value,
                                          String fileName) {
        List<ProductInContent> productInContentList =
            perfumePage
                .filterBy(HIGHLIGHTS, filterValue)
                .filterBy(perfumeFilter, value)
                .getProductContentPage()
                .getProductsInContent(limit).stream()
                .map(mapToProductContent).toList();

        convertToCSV(productInContentList, fileName);

        List<ProductInContent> expectedProductInContentList = convertCsvToObj(fileName);
        assertThat(expectedProductInContentList.get(0), isIn(productInContentList));
    }

    @DataProvider
    public Object[][] filterDataProvider() {
        return new Object[][] {
            {"Sale", 2, PRODUCTART, "Eau de Parfum", "sale_product_type.csv"},
//            {"Sale", 2, MARKE, "Abercrombie & Fitch", "sale_brand.csv"},
//            {"Sale", 2, FUR_WEN, "Männlich", "sale_for_whom.csv"},
//            {"NEU", 2, PRODUCTART, "Duftset", "new_product_type.csv"},
//            {"NEU", 1, FUR_WEN, "Unisex", "new_for_whom.csv"},
//            {"Limitiert", 1, PRODUCTART, "Eau de Toilette", "limited_product_type.csv"},
//            {"Limitiert", 1, MARKE, "Aigner", "limited_brand.csv"},
//            {"Limitiert", 1, GESCHENK_FUR, "Nikolaus", "limited_present_for.csv"},
//            {"Limitiert", 1, FUR_WEN, "Männlich", "limited_for_whom.csv"}
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