package com.micropos.orders.service;

import com.micropos.orders.model.Item;
import com.micropos.orders.model.Order;
import com.micropos.orders.repository.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.micropos.orders.OrdersConfig.EXCHANGE_NAME;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private OrderRepository orderRepository;

    public OrdersServiceImpl(@Autowired OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.getOrders();
    }

    @Override
    public Order getOrder(int OrderNo) {
        return orderRepository.getOrder(OrderNo);
    }

    @Override
    public int addOrder(List<Item> items) {
        int orderNo = orderRepository.addOrder(items);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, orderNo);
        return orderNo;
    }

}
