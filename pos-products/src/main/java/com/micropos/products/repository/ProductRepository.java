package com.micropos.products.repository;


import com.micropos.products.model.Product;

import java.io.IOException;
import java.util.List;

public interface ProductRepository {

    public List<Product> allProducts();

    public Product findProduct(String productId);

}