package com.upstreamsystems.trainee.challenge1.model;

import java.util.Map;

public class LargeOrderShippingCostCalculator implements ShippingCostCalculator{

    @Override
    public Integer calculateShippingCost(OrderSuggestion suggestion, Map<String,Integer> shippingCostPerShop){
                return 0 ;
    }

    @Override
    public Boolean isApllicable(OrderSuggestion suggestion,Order order){
        return suggestion.getTotalCost() > 100;
    }

}
