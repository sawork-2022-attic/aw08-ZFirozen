package com.micropos.orders.repository;

import com.micropos.orders.model.Item;
import com.micropos.orders.model.Order;

import java.util.List;

public interface OrderRepository {

    public List<Order> getOrders();
    public Order getOrder(int orderNo);
    public int addOrder(List<Item> items);

}
