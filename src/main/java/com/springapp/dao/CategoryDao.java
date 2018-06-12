package com.springapp.dao;

import com.springapp.model.Category;
import com.springapp.model.Product;

import java.util.List;
import java.util.Map;

public interface CategoryDao {
    Map<String, String> categoriesMap();
    void addNewProduct(Product product);
    List<Category> getCategoryList();
    void addCategory(Category category);
    void deleteCategory(String id);
    Category getCategoryById(String id);
    void updateCategory(Category category);
    int categoryValidator(String categoryName);
}
