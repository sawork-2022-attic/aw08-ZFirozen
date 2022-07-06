package com.micropos.orders.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderNo = 0;
    private List<Item> orderItems = new ArrayList<>();

    public boolean addOrderItem(Item item) {
        return orderItems.add(item);
    }

    public List<Item> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Item> items) {
        this.orderItems = items;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
}
