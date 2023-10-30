package com.douglas.de.pages.pojo;

import java.util.*;

public class ProductInContent {

    private String topBrand;
    private String brandLine;
    private String name;
    private String category;
    private String priceInfo;

    public String getTopBrand() {
        return topBrand;
    }

    public void setTopBrand(String topBrand) {
        this.topBrand = topBrand;
    }

    public String getBrandLine() {
        return brandLine;
    }

    public void setBrandLine(String brandLine) {
        this.brandLine = brandLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductInContent)) {
            return false;
        }
        ProductInContent content = (ProductInContent) o;
        return getTopBrand().equals(content.getTopBrand()) && getBrandLine().equals(content.getBrandLine())
               && getName().equals(content.getName()) && getCategory().equals(content.getCategory())
               && getPriceInfo().equals(content.getPriceInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTopBrand(), getBrandLine(), getName(), getCategory(), getPriceInfo());
    }
}
