package com.upstreamsystems.trainee.challenge1.model;

import java.util.Objects;

/**
 * A representation of the City entity
 */
public final class City {

  //Name of the city, used as identifier
  private final String name;
  //Shipping cost (euro cent) for the city, constant regardless the number of items
  private final int shippingCost;

  public City(String name, int shippingCost) {
    this.name = name;
    this.shippingCost = shippingCost;
  }

  public String getName() {
    return name;
  }

  public int getShippingCost() {
    return shippingCost;
  }

}
