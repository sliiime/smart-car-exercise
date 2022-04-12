package com.upstreamsystems.trainee.challenge1;

import com.upstreamsystems.trainee.challenge1.model.Order;
import com.upstreamsystems.trainee.challenge1.model.OrderSuggestion;
import com.upstreamsystems.trainee.challenge1.service.OrderService;
import com.upstreamsystems.trainee.challenge1.service.OrderServiceImpl;
import com.upstreamsystems.trainee.challenge1.utils.CSVReader;
import java.util.List;

public class OrderApplication {

  public static void main(String[] args) {
    OrderService service = new OrderServiceImpl(CSVReader.getShops(),
        CSVReader.getProductPrices(), CSVReader.getCities());

    int cnt = 1;
    List<String> orderProducts = List.of("product2", "product6", "product7");
    System.out.println("Ordered products:" + orderProducts);
    System.out.println("Suggestions:");
    for (OrderSuggestion suggestion : service.calculate(
        new Order("user1", orderProducts))) {
      System.out.println(cnt++ + " => " + suggestion);
    }
  }
}