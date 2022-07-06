package com.micropos.delivery.service;

import com.micropos.delivery.model.DeliveryOrder;

import java.util.List;

public interface DeliveryService {

    public List<DeliveryOrder> getOrders();
    public DeliveryOrder getOrder(int OrderNo);
    public boolean addOrder(int orderNo);

}
