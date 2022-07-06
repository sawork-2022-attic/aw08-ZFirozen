package com.micropos.products.rest;

import com.micropos.api.ApiUtil;
import com.micropos.api.ProductsApi;
import com.micropos.dto.ProductDto;
import com.micropos.products.mapper.ProductMapper;
import com.micropos.products.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class ProductController implements ProductsApi {

    private final ProductMapper productMapper;

    private final ProductService productService;


    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @Override
    @CrossOrigin
    public ResponseEntity<List<ProductDto>> listProducts(String category, Integer page){
//        List<ProductDto> products = new ArrayList<>(productMapper.toProductsDto(this.productService.products()));
        List<ProductDto> products = new ArrayList<>(
                productMapper.toProductsDto(this.productService.getProducts(category, page)));
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ProductDto> showProductById(String productId)
    {
        ProductDto productDto = productMapper.toProductDto(this.productService.getProduct(productId));
        if (productDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
}
