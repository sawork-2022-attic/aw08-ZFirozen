package com.micropos.carts.mapper;

import com.micropos.products.dto.CartProductDto;
import org.mapstruct.Mapper;
import com.micropos.carts.model.Item;

import java.util.Collection;

@Mapper
public interface CartMapper {

    Collection<CartProductDto> toCartProductDto(Collection<Item> items);

    Collection<Item> toItems(Collection<CartProductDto> cartProducts);
}
