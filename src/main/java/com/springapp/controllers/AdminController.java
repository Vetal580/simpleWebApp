package com.springapp.controllers;

import com.springapp.model.Product;
import com.springapp.model.Search;
import com.springapp.service.*;
import com.springapp.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class AdminController {
    @Autowired UserService userService;
    @Autowired CategoryService categoryService;
    @Autowired ProductService productService;
    @Autowired ProductValidation productValidation;
    @Autowired Search search;

    @InitBinder (value = "productVal")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(productValidation);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage(Model model) {
        LinkedHashMap<String, String> inStockList = productService.getInStockList();
        model.addAttribute("productVal", new Product());
        model.addAttribute("categoriesList", categoryService.getCategoriesMap());
        model.addAttribute("inStockList", inStockList);
        model.addAttribute("search", search);
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("productVal") @Validated Product product, BindingResult result, Model model) throws IOException {
        LinkedHashMap<String, String> inStockList = productService.getInStockList();
        model.addAttribute("categoriesList", categoryService.getCategoriesMap());
        model.addAttribute("inStockList", inStockList);
        model.addAttribute("search", search);
        if (result.hasErrors()){
            return "admin";
        } else {
            List<String> uploadImages = new ArrayList<>();
            if (product.getFiles().length > 1){
                uploadImages = productService.productImageUpload(product);
                product.setImagesList(uploadImages);
            } else {
                uploadImages.add("");
                product.setImagesList(uploadImages);
            }
            categoryService.addProduct(product);
            model.addAttribute("addedProduct", true);
            return "admin";
        }
    }

    @RequestMapping(value = "/admproductedit", method = RequestMethod.GET)
    public String editProduct(Model model){
        LinkedHashMap<String, String> categoryList = categoryService.getCategoriesMap();
        List<Product> fullProductsList = productService.getAllProducts();
        model.addAttribute("allProducts", fullProductsList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("search", search);
        return "admproductedit";
    }

    @RequestMapping(value = "/edit-product/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable String id, Model model){
        Product productById = productService.getProductById(id);
        LinkedHashMap<String, String> categoryList = categoryService.getCategoriesMap();
        LinkedHashMap<String, String> inStockList = productService.getInStockList();
        model.addAttribute("search", search);
        model.addAttribute("product", productById);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("inStockList", inStockList);
        model.addAttribute("productVal", productById);
        return "admseparateprodedit";
    }

    @RequestMapping(value = "/edit-product/{id}", method = RequestMethod.POST)
    public String editProductResult(@ModelAttribute ("productVal") @Validated Product product, BindingResult result, Model model){
        LinkedHashMap<String, String> categoryList = categoryService.getCategoriesMap();
        LinkedHashMap<String, String> inStockList = productService.getInStockList();
        model.addAttribute("search", search);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("inStockList", inStockList);
        if (result.hasErrors()){
            return "admseparateprodedit";
        } else {
            productService.updateProduct(product);
            model.addAttribute("updated", "Product was successful updated!");
            return "admseparateprodedit";
        }
    }

    @RequestMapping (value = "/deleteproduct/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return "admproductedit";
    }
}