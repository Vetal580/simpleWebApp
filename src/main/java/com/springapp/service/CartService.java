package com.springapp.service;

import com.springapp.model.Product;

import java.util.List;

public interface CartService {
    List<Product> getProductList();
    void setProductList(List<Product> productList);
    void addProductToCart(String id);
}
