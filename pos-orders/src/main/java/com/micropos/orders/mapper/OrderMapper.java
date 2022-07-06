package com.micropos.orders.mapper;

import com.micropos.orders.model.Order;
import com.micropos.products.dto.CartProductDto;
import com.micropos.products.dto.OrderDto;
import com.micropos.orders.model.Item;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface OrderMapper {

    Collection<OrderDto> toOrderDto(Collection<Order> order);

    Collection<Order> toOrder(Collection<OrderDto> order);

    OrderDto toOrderDto(Order order);

    Order toOrder(OrderDto order);

    Collection<CartProductDto> toCartProductDto(Collection<Item> items);

    Collection<Item> toItems(Collection<CartProductDto> cartProducts);
}
