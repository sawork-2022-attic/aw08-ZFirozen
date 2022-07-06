package com.micropos.delivery.model;

public class DeliveryOrder {

    private int orderNo = 0;
    private int deliveryStage = 0;

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getDeliveryStage() {
        return deliveryStage;
    }

    public void setDeliveryStage(int deliveryStage) {
        this.deliveryStage = deliveryStage;
    }
}
