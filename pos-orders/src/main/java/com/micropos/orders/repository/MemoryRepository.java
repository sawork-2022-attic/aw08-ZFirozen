package com.micropos.orders.repository;

import com.micropos.orders.model.Order;
import com.micropos.orders.model.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public Order getOrder(int orderNo) {
        for (Order order: orders) {
            if (order.getOrderNo() == orderNo) {
                return order;
            }
        }
        return null;
    }

    @Override
    public int addOrder(List<Item> items) {
        Order newOrder = new Order();
        double totalPrice = 0.0;
        for (Item item: items) {
            newOrder.addOrderItem(item);
            // totalPrice += item.getPrice() * item.getQuantity();
        }
        // newOrder.setTotalPrice(totalPrice);
        int orderNo = orders.size();
        newOrder.setOrderNo(orderNo);
        orders.add(newOrder);
        return orderNo;
    }

}
