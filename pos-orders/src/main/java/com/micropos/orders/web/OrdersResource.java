package com.micropos.orders.web;

import com.micropos.orders.mapper.OrderMapper;
import com.micropos.orders.model.Order;
import com.micropos.orders.service.OrdersService;
import com.micropos.products.api.OrderCreateApi;
import com.micropos.products.api.OrderItemsApi;
import com.micropos.products.api.OrdersApi;
import com.micropos.products.dto.CartProductDto;
import com.micropos.products.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class OrdersResource implements OrdersApi, OrderCreateApi, OrderItemsApi {

    private final OrderMapper orderMapper;

    private final OrdersService ordersService;

    public OrdersResource(OrdersService ordersService, OrderMapper orderMapper) {
        this.ordersService = ordersService;
        this.orderMapper = orderMapper;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return OrdersApi.super.getRequest();
    }

    @RequestMapping(value = "orders", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OrderDto>> listOrder() {
        List<OrderDto> ordersDto = new ArrayList<>(orderMapper.toOrderDto((this.ordersService.getOrders())));
        return new ResponseEntity<>(ordersDto, HttpStatus.OK);
    }

    @RequestMapping(value = "order_items/{orderId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OrderDto> listOrderItems(@PathVariable String orderId) {
        int orderIdInt = Integer.parseInt(orderId);
        return new ResponseEntity<>(orderMapper.toOrderDto((this.ordersService.getOrder(orderIdInt))), HttpStatus.OK);
    }

    @RequestMapping(value = "order_create", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Integer> createOrder(List<CartProductDto> products) {
        return new ResponseEntity<>(this.ordersService.addOrder(new ArrayList<>(orderMapper.toItems(products))), HttpStatus.OK);
    }

}
