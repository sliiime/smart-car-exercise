package com.upstreamsystems.trainee.challenge1.model;

import java.util.HashSet;
import java.util.Map;

public class DefaultShippingCostCalculator implements ShippingCostCalculator {

    @Override
    public Integer calculateShippingCost(OrderSuggestion suggestion, Map<String, Integer> shippingCostPerShop) {

        HashSet<String> shopSet = new HashSet<>(suggestion.getShopNames());

        int totalShippingCost = 0;
        for (String shop : shopSet) totalShippingCost += shippingCostPerShop.get(shop);

        return totalShippingCost;
    }

    @Override
    public Boolean isApllicable(OrderSuggestion suggestion, Order order) {
            return true;
    }

}
