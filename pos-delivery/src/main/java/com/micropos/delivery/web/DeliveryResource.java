package com.micropos.delivery.web;

import com.micropos.delivery.mapper.DeliveryMapper;
import com.micropos.delivery.model.DeliveryOrder;
import com.micropos.delivery.service.DeliveryService;
import com.micropos.products.api.DeliveriesApi;
import com.micropos.products.api.DeliveryApi;
import com.micropos.products.dto.DeliveryOrderDto;
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
public class DeliveryResource implements DeliveryApi, DeliveriesApi {

    private final DeliveryMapper deliveryMapper;

    private final DeliveryService deliveryService;

    public DeliveryResource(DeliveryService deliveryService, DeliveryMapper deliveryMapper) {
        this.deliveryService = deliveryService;
        this.deliveryMapper = deliveryMapper;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return DeliveryApi.super.getRequest();
    }

    @RequestMapping(value = "deliveries", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<DeliveryOrderDto>> listDeliveryOrder() {
        List<DeliveryOrderDto> ordersDto = new ArrayList<>(deliveryMapper.toDeliveryOrderDto(deliveryService.getOrders()));
        return new ResponseEntity<>(ordersDto, HttpStatus.OK);
    }

    @RequestMapping(value = "delivery/{orderId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<DeliveryOrderDto> getDeliveryOrder(@PathVariable String orderId) {
        int orderIdInt = Integer.parseInt(orderId);
        return new ResponseEntity<>(deliveryMapper.toDeliveryOrderDto(deliveryService.getOrder(orderIdInt)), HttpStatus.OK);
    }
}
