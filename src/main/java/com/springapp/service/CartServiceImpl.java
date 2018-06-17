package com.springapp.service;

import com.springapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements Serializable, CartService{
    @Autowired
    ProductService productService;
    private List<Product> productList = new ArrayList<>();

    @Override
    public List<Product> getProductList() {
        return this.productList;
    }

    @Override
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public void addProductToCart(String id){
        Product product = productService.getProductById(id);
        productList.add(product);
    }

}
