package com.upstreamsystems.trainee.challenge1.model;

import java.util.Objects;

/**
 * A representation of the product/price entity, models the
 * availability of a product in a shop along with its price
 */
public final class ProductPrice {

  //the name of the product, used as identifier
  private final String productName;
  //the name of the shop selling this item
  private final String shopName;
  //the price (euro cent) of the product in the certain shop
  private final int price;

  public ProductPrice(String productName, String shopName, int price) {
    this.productName = productName;
    this.shopName = shopName;
    this.price = price;
  }

  public String getProductName() {
    return productName;
  }

  public String getShopName() {
    return shopName;
  }

  public int getPrice() {
    return price;
  }

}
