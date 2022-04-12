package com.upstreamsystems.trainee.challenge1.utils;

import com.upstreamsystems.trainee.challenge1.model.City;
import com.upstreamsystems.trainee.challenge1.model.ProductPrice;
import com.upstreamsystems.trainee.challenge1.model.Shop;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class CSVReaderTester {

    private String PRODUCTS_FILE;
    private String SHOPS_FILE;
    private String CITIES_FILE;

    private List<Shop> shops;
    private List<ProductPrice> productPrices;
    private List<City> cities;

    /**
     * Get shops data as a list
     *
     * @return list of shops
     */

    public CSVReaderTester(String productsFile, String shopsFile, String citiesFile){
        this.PRODUCTS_FILE = productsFile;
        this.SHOPS_FILE = shopsFile;
        this.CITIES_FILE = citiesFile;
    }

    public List<Shop> getShops() {
        if (shops == null) {
            shops = readFile(SHOPS_FILE)
                    .map(arr -> new Shop(arr[0], Integer.parseInt(arr[1]), arr[2]))
                    .toList();
        }
        return shops;
    }

    /**
     * Get product prices data as a list
     *
     * @return list of product prices
     */
    public List<ProductPrice> getProductPrices() {
        if (productPrices == null) {
            productPrices = readFile(PRODUCTS_FILE)
                    .map(arr -> new ProductPrice(arr[0], arr[1], Integer.parseInt(arr[2])))
                    .toList();
        }
        return productPrices;
    }

    /**
     * Get cities data as a list
     *
     * @return list of cities
     */
    public List<City> getCities() {
        if (cities == null) {
            cities = readFile(CITIES_FILE)
                    .map(arr -> new City(arr[0], Integer.parseInt(arr[1])))
                    .toList();
        }
        return cities;
    }

    /**
     * Get contents of a CSV file separated by comma as a string array.
     * No header should exist in CSV file.
     *
     * @param filename CVS file to read
     * @return content of file as stream of String[], where each element in
     * array is a csv field. In case of error, an empty stream
     */
    private Stream<String[]> readFile(String filename) {
        try {
            return Files.readAllLines(Paths.get(CSVReader.class.getResource(filename).toURI()))
                    .stream().map(s -> s.trim().split(","));
        } catch (Exception e) {
            return Stream.of();
        }

    }
}
