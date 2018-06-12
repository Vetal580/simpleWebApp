package com.springapp.service;

import com.springapp.dao.CategoryDaoImpl;
import com.springapp.model.Category;
import com.springapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryDaoImpl categoryDaoImpl;

    @Override
    public LinkedHashMap<String, String> getCategoriesMap(){
        LinkedHashMap<String, String> categoryMap = (LinkedHashMap<String, String>) categoryDaoImpl.categoriesMap();
        return categoryMap;
    }

    @Override
    public void addProduct(Product product){
        categoryDaoImpl.addNewProduct(product);
    }

    @Override
    public List<Category> getCategoryList(){
        return categoryDaoImpl.getCategoryList();
    }

    @Override
    public void addCategory(Category category){
        categoryDaoImpl.addCategory(category);
    }

    @Override
    public void deleteCategory(String id){
        categoryDaoImpl.deleteCategory(id);
    }

    @Override
    public Category getCategoryById(String id){
        return categoryDaoImpl.getCategoryById(id);
    }

    @Override
    public void updateCategory(Category category){
        categoryDaoImpl.updateCategory(category);
    }

    @Override
    public int categoryValidation(String categoryName){
        return categoryDaoImpl.categoryValidator(categoryName);
    }
}
