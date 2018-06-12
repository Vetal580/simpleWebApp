package com.springapp.service;

import com.springapp.model.Product;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public interface ProductService {
    List<Product> getProductsFromCategory(String id);
    Product getProductById(String id);
    List<Product> getAllProducts();
    List<String> productImageUpload(Product product) throws IOException;
    LinkedHashMap<String, String> getInStockList();
    void updateProduct(Product product);
    void deleteProduct(String id);
}
