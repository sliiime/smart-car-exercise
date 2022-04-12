package com.upstreamsystems.trainee.challenge1.model;

import java.util.Objects;

/**
 * A representation of the Shop entity
 */
public final class Shop {

  //Name of the shop, used as identifier
  private final String name;
  //Shipping cost (euro cent) for the shop, constant regardless the number of items
  private final int shippingCost;
  //Name of the city this shop belongs to
  private final String city;

  public Shop(String name, int shippingCost, String city) {
    this.name = name;
    this.shippingCost = shippingCost;
    this.city = city;
  }

  public String getName() {
    return name;
  }

  public int getShippingCost() {
    return shippingCost;
  }

  public String getCity() {
    return this.city;
  }

  @Override
  public String toString() {
    return "Shop[" +
        "name=" + name + ", " +
        "shippingCost=" + shippingCost + ']';
  }
}
