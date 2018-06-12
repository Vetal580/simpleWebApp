package com.springapp.service;

import com.springapp.model.Category;
import com.springapp.model.Product;

import java.util.LinkedHashMap;
import java.util.List;

public interface CategoryService {
    LinkedHashMap<String, String> getCategoriesMap();
    void addProduct(Product product);
    List<Category> getCategoryList();
    void addCategory(Category category);
    void deleteCategory(String id);
    Category getCategoryById(String id);
    void updateCategory(Category category);
    int categoryValidation(String categoryName);
}
