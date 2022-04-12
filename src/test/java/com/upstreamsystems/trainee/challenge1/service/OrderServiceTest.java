package com.upstreamsystems.trainee.challenge1.service;

import static org.junit.Assert.assertEquals;

import com.upstreamsystems.trainee.challenge1.model.Order;
import com.upstreamsystems.trainee.challenge1.model.OrderSuggestion;
import com.upstreamsystems.trainee.challenge1.utils.CSVReader;
import java.util.List;

import com.upstreamsystems.trainee.challenge1.utils.CSVReaderTester;
import org.junit.Before;
import org.junit.Test;

public class OrderServiceTest {

  OrderService service;

  @Before
  public void init() {
    service = new OrderServiceImpl(CSVReader.getShops(),
        CSVReader.getProductPrices(), CSVReader.getCities());
  }

//  @Test
//  public void test_combinations() throws Exception {
//    List<OrderSuggestion> suggestions = service.calculate(
//        new Order("user1", List.of("product1", "product3")));
//    assertEquals(15, suggestions.size());
//  }

  @Test
  public void test_non_existing_product() throws Exception {
    List<OrderSuggestion> suggestions = service.calculate(
        new Order("user1", List.of("product_non_exist1", "product_non_exist2")));
    assertEquals(0, suggestions.size());
  }

  @Test
  public void test_discount_is_applied() throws Exception {
    CSVReaderTester csvReaderTester = new CSVReaderTester("/productstest1.csv","/shopstest1.csv","/citiestest1.csv");
    OrderService service = new OrderServiceImpl(csvReaderTester.getShops(),
            csvReaderTester.getProductPrices(), csvReaderTester.getCities());

    List<String> orderProducts = List.of("product1");

    List<OrderSuggestion> orderSuggestions = service.calculate(new Order("user1",orderProducts));

    int[] cost = {120,500,580};
    int cnt = 0;
    for (OrderSuggestion suggestion : orderSuggestions){
      assertEquals(cost[cnt],suggestion.getTotalCost());
      System.out.println(cnt++ + "=> " +suggestion);
    }
  }

}
