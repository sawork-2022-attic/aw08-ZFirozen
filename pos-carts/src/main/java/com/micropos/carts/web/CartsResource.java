package com.micropos.carts.web;

import com.micropos.carts.mapper.CartMapper;
import com.micropos.carts.model.Item;
import com.micropos.carts.service.CartsService;
import com.micropos.products.api.CartsApi;
import com.micropos.products.api.CartsdelApi;
import com.micropos.products.dto.CartProductDto;
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
public class CartsResource implements CartsApi, CartsdelApi {

    private final CartMapper cartMapper;

    private final CartsService cartsService;

    public CartsResource(CartsService cartsService, CartMapper cartMapper) {
        this.cartsService = cartsService;
        this.cartMapper = cartMapper;
    }

    @RequestMapping(value = "carts", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CartProductDto>> listCart() {
        List<CartProductDto> products = new ArrayList<>(cartMapper.toCartProductDto(cartsService.getCart().getItems()));
        List<Item> items = cartsService.getCart().getItems();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "carts/{productId}/{quantity}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CartProductDto>> addProduct(@PathVariable String productId, @PathVariable String quantity) {
        int quantityInt = Integer.parseInt(quantity);
        if (cartsService.putProduct(productId, quantityInt)) {
            List<CartProductDto> products = new ArrayList<>(cartMapper.toCartProductDto(cartsService.getCart().getItems()));
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "carts/{productId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Integer> getProductQuantity(@PathVariable String productId) {
        return new ResponseEntity<>(cartsService.getProductQuantity(productId), HttpStatus.OK);
    }

    @RequestMapping(value = "cartsdel/{productId}/{quantity}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<List<CartProductDto>> deleteProduct(@PathVariable String productId, @PathVariable String quantity) {
        int quantityInt = Integer.parseInt(quantity);
        if (cartsService.deleteProduct(productId, quantityInt)) {
            List<CartProductDto> products = new ArrayList<>(cartMapper.toCartProductDto(cartsService.getCart().getItems()));
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return CartsApi.super.getRequest();
    }

}
