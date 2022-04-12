package com.upstreamsystems.trainee.challenge1.service;

import com.upstreamsystems.trainee.challenge1.model.Order;
import com.upstreamsystems.trainee.challenge1.model.OrderSuggestion;
import java.util.List;

/**
 * A class containing functionality regarding manipulation of orders
 */
public interface OrderService {

  /**
   * Computes suggestions for the given order, returning results
   * that fulfill the given ordered by cost ascending
   * @param order The given order
   * @return List of suggestions fulfilling the order, order by cost ascending
   */
  List<OrderSuggestion> calculate(Order order);
}
