package com.micropos.delivery.mapper;

import com.micropos.products.dto.DeliveryOrderDto;
import com.micropos.delivery.model.DeliveryOrder;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface DeliveryMapper {

    Collection<DeliveryOrderDto> toDeliveryOrderDto(Collection<DeliveryOrder> orders);

    Collection<DeliveryOrder> toDeliveryOrder(Collection<DeliveryOrderDto> orders);

    DeliveryOrder toDeliveryOrder(DeliveryOrderDto orderDto);

    DeliveryOrderDto toDeliveryOrderDto(DeliveryOrder order);
}
