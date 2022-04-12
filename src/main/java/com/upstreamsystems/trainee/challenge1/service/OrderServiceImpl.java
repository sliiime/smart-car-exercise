package com.upstreamsystems.trainee.challenge1.service;

import com.upstreamsystems.trainee.challenge1.model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * A dummy implementation of the order service, keeping all
 * data in memory
 */
public class OrderServiceImpl implements OrderService {

    //maximum number of suggestions to be returned
    private final int RESULTS_LIMIT = 30;

    //mapping of shops->shipping cost
    private Map<String, Integer> shippingCostPerShop;
    //mapping of product to price per shop: product-> (shop->price)
    private Map<String, Map<String, Integer>> productPriceMap;
    //list of cities
    private List<City> cities;

    public OrderServiceImpl(List<Shop> shops, List<ProductPrice> productPrices, List<City> cities) {
        //converts the list of shops to the appropriate mapping
        this.shippingCostPerShop = shops.stream().collect(Collectors.toMap(Shop::getName, Shop::getShippingCost));

        //converts the list of product/price to the appropriate mapping
        this.productPriceMap = productPrices.stream().collect(Collectors.groupingBy(ProductPrice::getProductName))
                .entrySet().stream().collect(Collectors.toMap(Entry::getKey, entry ->
                        entry.getValue().stream().collect(Collectors.toMap(ProductPrice::getShopName, ProductPrice::getPrice))
                ));

        this.cities = cities;
    }

    /**
     * Computes the order suggestions based on the in memoty mappings
     *
     * @param order The given order
     * @return The order suggestions order by cost ascending
     */
    @Override
    public List<OrderSuggestion> calculate(Order order) {
        //get all possible product/shop combinations
        //returned cost includes just the product prices
        List<OrderSuggestion> orderSuggestions = calculateSuggestions(order.getProductsNames());

        LargeOrderShippingCostCalculator largeOrderShippingCostCalculator = new LargeOrderShippingCostCalculator();
        DefaultShippingCostCalculator defaultShippingCostCalculator = new DefaultShippingCostCalculator();
        PremiumShippingCostCalculator premiumShippingCostCalculator = new PremiumShippingCostCalculator();

        ShippingCostCalculator shippingCostCalculator;

        //iterate the combinations and add shipping costs
        for (OrderSuggestion suggestion : orderSuggestions) {
            if (largeOrderShippingCostCalculator.isApplicable(suggestion, order))
                shippingCostCalculator = largeOrderShippingCostCalculator;
            else if (premiumShippingCostCalculator.isApplicable(suggestion, order))
                shippingCostCalculator = premiumShippingCostCalculator;
            else
                shippingCostCalculator = defaultShippingCostCalculator;

            suggestion.setTotalCost(suggestion.getTotalCost() + shippingCostCalculator.calculateShippingCost(suggestion, shippingCostPerShop));

        }
        //sort result by cost ascending
        orderSuggestions.sort(Comparator.comparingInt(OrderSuggestion::getTotalCost));
        //return result, limited to the specified size
        return orderSuggestions.size() > RESULTS_LIMIT ? orderSuggestions.subList(0, RESULTS_LIMIT) : orderSuggestions;

    }

    /**
     * ********THIS METHOD SHOULD NOT BE MODIFIED********
     * <p>
     * Compute all possible combinations of shops that can fulfill the given order
     */
    private List<OrderSuggestion> calculateSuggestions(List<String> productsName) {
        // ********THIS METHOD SHOULD NOT BE MODIFIED********
        List<OrderSuggestion> suggestions = new ArrayList<>();

        //get all possible product/shop combinations
        //returned cost includes just the product prices
        calculateSuggestions(productsName, suggestions, new OrderSuggestion(new ArrayList<>(), 0));

        return suggestions;
    }

    /**
     * ********THIS METHOD SHOULD NOT BE MODIFIED********
     * <p>
     * Recursive method to compute all possible combinations of shops that can
     * fulfill the given order
     *
     * @param productsNames     The list of products in order
     * @param suggestions       The computed list of suggestions
     * @param currentSuggestion The currently processing suggestion of the recursion
     */
    private void calculateSuggestions(List<String> productsNames, final List<OrderSuggestion> suggestions, OrderSuggestion currentSuggestion) {
        //********THIS METHOD SHOULD NOT BE MODIFIED********
        //end of recursion, add current suggestion to the list
        if (productsNames.size() == 0) {
            suggestions.add(currentSuggestion);
            return;
        }

        List<String> copyProductCodes = new ArrayList<>(productsNames);
        //pop the first product of the list
        String productInOrder = copyProductCodes.remove(0);
        //for each shop that sells the product retrieved above, create a suggestion
        productPriceMap.getOrDefault(productInOrder, Map.of()).forEach((shop, price) -> {
            OrderSuggestion currentSuggestionCopy = new OrderSuggestion(currentSuggestion);
            //add the product to the suggestion
            currentSuggestionCopy.getShopNames().add(shop);
            //increase total cost by the price of the product in the certain shop
            currentSuggestionCopy.setTotalCost(currentSuggestion.getTotalCost() + price);
            //propagate recursively to the rest of the products in the order
            calculateSuggestions(copyProductCodes, suggestions, currentSuggestionCopy);
        });
        //********THIS METHOD SHOULD NOT BE MODIFIED********
    }

}
