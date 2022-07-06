package com.micropos.delivery.repository;

import com.micropos.delivery.model.DeliveryOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryRepository implements DeliveryRepository {

    private List<DeliveryOrder> orders = new ArrayList<>();

    @Override
    public List<DeliveryOrder> getOrders() {
        return orders;
    }

    @Override
    public DeliveryOrder getOrder(int orderNo) {
        for (DeliveryOrder order: orders) {
            if (order.getOrderNo() == orderNo) {
                return order;
            }
        }
        return null;
    }

    @Override
    public boolean addOrder(int orderNo) {
        for (DeliveryOrder order: orders) {
            if (order.getOrderNo() == orderNo) {
                return false;
            }
        }
        DeliveryOrder newOrder = new DeliveryOrder();
        newOrder.setOrderNo(orderNo);
        newOrder.setDeliveryStage(0);
        orders.add(newOrder);
        return true;
    }

}
