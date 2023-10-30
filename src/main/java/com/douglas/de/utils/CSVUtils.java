package com.douglas.de.utils;

import com.douglas.de.pages.pojo.ProductInContent;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.*;

public class CSVUtils {

    public static void convertToCSV(List<ProductInContent> products, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            String[] headers = {"top_brand", "brand_line", "name", "category", "price_info"};
            writer.writeNext(headers);

            products.forEach(product -> {
                String[] data = {product.getTopBrand(), product.getBrandLine(), product.getName(), product.getCategory(), product.getPriceInfo()};
                writer.writeNext(data);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<ProductInContent> convertCsvToObj(String fileName) {
        List<ProductInContent> productInContentList = new ArrayList<>();
        String csvFile = "src/main/resources/test_data/" + fileName + "";
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFile))) {
            csvReader.readNext();
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                ProductInContent product = new ProductInContent();
                product.setTopBrand(record[0]);
                product.setBrandLine(record[1]);
                product.setName(record[2]);
                product.setCategory(record[3]);
                product.setPriceInfo(record[4]);

                productInContentList.add(product);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return productInContentList;
    }
}
