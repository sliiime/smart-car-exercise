package com.upstreamsystems.trainee.challenge1.model;

import java.util.Map;

public interface ShippingCostCalculator {

    public Integer calculateShippingCost(OrderSuggestion suggestion, Map<String,Integer> shippingCostPerShop);
    public Boolean isApplicable(OrderSuggestion suggestion, Order order);
}
