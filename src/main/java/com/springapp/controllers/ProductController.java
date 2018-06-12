package com.springapp.controllers;

import com.springapp.model.Category;
import com.springapp.model.Product;
import com.springapp.service.CartServiceImpl;
import com.springapp.service.CategoryServiceImpl;
import com.springapp.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Controller
public class ProductController {
    @Autowired ProductServiceImpl productServiceImpl;
    @Autowired CartServiceImpl cartServiceImpl;
    @Autowired CategoryServiceImpl categoryServiceImpl;

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String getProductFromCategory(@PathVariable (value = "id") String id, Model model){
        ArrayList<Category> categoryList = (ArrayList<Category>) categoryServiceImpl.getCategoryList();
        model.addAttribute("categories", categoryList);
        model.addAttribute("product", productServiceImpl.getProductsFromCategory(id));
        return "category";
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String getProductPage(@PathVariable (value = "id") String id, Model model){
        Product product = productServiceImpl.getProductById(id);
        LinkedHashMap<String, String> categoryList = categoryServiceImpl.getCategoriesMap();
        model.addAttribute(product);
        model.addAttribute("categoryName", categoryList.get(product.getCategory()));
        return "product";
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/add-to-cart/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void addProductToCart(@PathVariable (value = "id") String id){
        cartServiceImpl.addProductToCart(id);
    }
}
