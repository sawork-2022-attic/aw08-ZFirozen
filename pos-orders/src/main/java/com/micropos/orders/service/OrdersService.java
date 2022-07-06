package com.micropos.orders.service;

import com.micropos.orders.model.Item;
import com.micropos.orders.model.Order;

import java.util.List;

public interface OrdersService {

    public List<Order> getOrders();
    public Order getOrder(int OrderNo);
    public int addOrder(List<Item> items);

}
