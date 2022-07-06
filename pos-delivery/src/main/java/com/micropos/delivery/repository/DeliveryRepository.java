package com.micropos.delivery.repository;

import com.micropos.delivery.model.DeliveryOrder;

import java.util.List;

public interface DeliveryRepository {

    public List<DeliveryOrder> getOrders();
    public DeliveryOrder getOrder(int orderNo);
    public boolean addOrder(int orderNo);

}
