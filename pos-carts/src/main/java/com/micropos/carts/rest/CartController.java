package com.micropos.carts.rest;

import com.micropos.api.CartsApi;
import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Product;
import com.micropos.carts.service.CartService;
import com.micropos.dto.ItemDto;
import com.micropos.dto.CartDto;
import com.micropos.carts.mapper.CartMapper;
import com.micropos.carts.model.Item;
import com.micropos.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin
public class CartController implements CartsApi {

    private final CartMapper cartMapper;

    private final CartService cartService;

    public CartController(CartService cartService, CartMapper cartMapper) {
        this.cartMapper = cartMapper;
        this.cartService = cartService;
        System.out.println("cart constructed");
    }

    @Override
    public ResponseEntity<CartDto> createCart(CartDto cartDto) {
        Cart cart = cartMapper.toCart(cartDto);
        if (!cartService.addCart(cart)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<CartDto>> listCarts() {
        List<CartDto> cartsDto = (List<CartDto>) cartMapper.toCartsDto(cartService.getCarts());
        return new ResponseEntity<>(cartsDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> showCartById(Integer cartId) {
        Cart cart = this.cartService.getCart(cartId);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CartDto cartDto = cartMapper.toCartDto(cart);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<CartDto> addItemToCart(Integer userId, ItemDto itemDto) {
//        Item item = cartMapper.toItem(itemDto);
//        Cart cart = cartService.getCart(userId);
//        if (cart == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        cartService.add(userId, item);
//        CartDto cartDto = cartMapper.toCartDto(cart);
//        return new ResponseEntity<>(cartDto, HttpStatus.OK);
//    }

    @Override
    public ResponseEntity<CartDto> addProductToCart(Integer cartId,
                                                    ProductDto productDto
    ) {
        Product product = cartMapper.toProduct(productDto);
        Cart cart = cartService.getCart(cartId);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartService.add(cartId, product);
        CartDto cartDto = cartMapper.toCartDto(cart);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Double> showCartTotal (Integer cartId) {
        Cart cart = cartService.getCart(cartId);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Double res = cartService.getTotal(cartId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
