package com.upstreamsystems.trainee.challenge1.model;

import java.util.List;
import java.util.Objects;

/**
 * A representation of the Order entity, models the
 * order from a user, requesting a list of products
 */
public final class Order {

  //identifier of the user that issued the order
  private final String username;
  //the names of the requested products
  private final List<String> productsNames;
  //defines the way the cost of the order is calculated
  private final OrderCalculator orderCalculator ;


  public Order(String username, List<String> productsNames) {
    this.username = username;
    this.productsNames = productsNames;
    this.orderCalculator = (x) ->{
      int sum = 0;
      for (String productName : x.productsNames) sum +=
    }
  }

  public Order(String username, List<String> productNames, OrderCalculator orderCalculator){
    this.username = username;
    this.productsNames = productNames;
    this.orderCalculator = orderCalculator;
  }


  public String getUsername() {
    return username;
  }

  public List<String> getProductsNames() {
    return productsNames;
  }

}
