package com.springapp.controllers;

import com.springapp.model.Category;
import com.springapp.model.Product;
import com.springapp.model.Search;
import com.springapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Controller
public class ProductController {
    @Autowired ProductService productService;
    @Autowired CartService cartService;
    @Autowired CategoryService categoryService;
    @Autowired Search search;

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String getProductFromCategory(@PathVariable (value = "id") String id, Model model){
        ArrayList<Category> categoryList = (ArrayList<Category>) categoryService.getCategoryList();
        model.addAttribute("categories", categoryList);
        model.addAttribute("search", search);
        model.addAttribute("product", productService.getProductsFromCategory(id));
        return "category";
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String getProductPage(@PathVariable (value = "id") String id, Model model){
        Product product = productService.getProductById(id);
        LinkedHashMap<String, String> categoryList = categoryService.getCategoriesMap();
        model.addAttribute("product", product);
        model.addAttribute("categoryName", categoryList.get(product.getCategory()));
        model.addAttribute("search", search);
        return "product";
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/add-to-cart/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void addProductToCart(@PathVariable (value = "id") String id){
        cartService.addProductToCart(id);
    }
}
