package com.micropos.delivery.service;

import com.micropos.delivery.model.DeliveryOrder;
import com.micropos.delivery.repository.DeliveryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.micropos.delivery.DeliveryConfig.QUEUE_NAME;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(@Autowired DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public List<DeliveryOrder> getOrders() {
        return deliveryRepository.getOrders();
    }

    @Override
    public DeliveryOrder getOrder(int OrderNo) {
        return deliveryRepository.getOrder(OrderNo);
    }

    @Override
    public boolean addOrder(int OrderNo) {
        return deliveryRepository.addOrder(OrderNo);
    }

    @RabbitListener(queues = { QUEUE_NAME })
    public void receiveDeliveryOrder(int orderNo) {
        addOrder(orderNo);
    }

}
