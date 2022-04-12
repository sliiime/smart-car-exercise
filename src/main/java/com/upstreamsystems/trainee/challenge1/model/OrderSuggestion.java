package com.upstreamsystems.trainee.challenge1.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of the suggestion fulfilling a
 * user order as computed by the application
 */
public class OrderSuggestion {
  //the list of shop names that will fulfill the order, mapping 1-1 to the product list in order
  private List<String> shopNames;
  //the total cost (euro cent) of the order, including the shipping costs
  private int totalCost;

  public OrderSuggestion(List<String> shopNames, int totalCost) {
    this.shopNames = shopNames;
    this.totalCost = totalCost;
  }

  public OrderSuggestion(OrderSuggestion orderSuggestion) {
    this.shopNames = new ArrayList<>(orderSuggestion.shopNames);
    this.totalCost = orderSuggestion.totalCost;
  }

  public List<String> getShopNames() {
    return shopNames;
  }

  public void setShopNames(List<String> shopNames) {
    this.shopNames = shopNames;
  }

  public int getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(int totalCost) {
    this.totalCost = totalCost;
  }

  @Override
  public String toString() {
    return "OrderSuggestion{" +
        "shopNames=" + shopNames +
        ", totalCost=" + totalCost +
        '}';
  }
}
