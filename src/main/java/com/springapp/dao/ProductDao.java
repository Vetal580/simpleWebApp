package com.springapp.dao;

import com.springapp.model.Product;
import java.util.List;
import java.util.Map;

public interface ProductDao {
    List<Product> getProductByCategory(String categoryId);
    Product getProductById(String id);
    List<Product> getAllProducts();
    Map<String, String> getInStockList();
    void updateProduct(Product product);
    void deleteProduct(String id);
}
