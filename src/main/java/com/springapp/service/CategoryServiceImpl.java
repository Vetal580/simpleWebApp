package com.springapp.service;

import com.springapp.dao.CategoryDao;
import com.springapp.model.Category;
import com.springapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryDao categoryDao;

    @Override
    public LinkedHashMap<String, String> getCategoriesMap(){
        LinkedHashMap<String, String> categoryMap = (LinkedHashMap<String, String>) categoryDao.categoriesMap();
        return categoryMap;
    }

    @Override
    public void addProduct(Product product){
        categoryDao.addNewProduct(product);
    }

    @Override
    public List<Category> getCategoryList(){
        return categoryDao.getCategoryList();
    }

    @Override
    public void addCategory(Category category){
        categoryDao.addCategory(category);
    }

    @Override
    public void deleteCategory(String id){
        categoryDao.deleteCategory(id);
    }

    @Override
    public Category getCategoryById(String id){
        return categoryDao.getCategoryById(id);
    }

    @Override
    public void updateCategory(Category category){
        categoryDao.updateCategory(category);
    }

    @Override
    public int categoryValidation(String categoryName){
        return categoryDao.categoryValidator(categoryName);
    }
}
