package com.springapp.controllers;

import com.springapp.model.Product;
import com.springapp.service.CategoryServiceImpl;
import com.springapp.service.ProductServiceImpl;
import com.springapp.service.UserServiceImpl;
import com.springapp.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class AdminController {
    @Autowired UserServiceImpl userServiceImpl;
    @Autowired CategoryServiceImpl categoryServiceImpl;
    @Autowired ProductServiceImpl productServiceImpl;
    @Autowired ProductValidation productValidation;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(productValidation);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView getAdminPage(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        LinkedHashMap<String, String> inStockList = productServiceImpl.getInStockList();
        modelAndView.addObject("admAddProduct", new Product());
        modelAndView.setViewName("admin");
        model.addAttribute("categoriesList", categoryServiceImpl.getCategoriesMap());
        model.addAttribute("inStockList", inStockList);
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("admAddProduct") @Validated Product product, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()){
            LinkedHashMap<String, String> inStockList = productServiceImpl.getInStockList();
            model.addAttribute("categoriesList", categoryServiceImpl.getCategoriesMap());
            model.addAttribute("inStockList", inStockList);
            return "admin";
        } else {
            LinkedHashMap<String, String> inStockList = productServiceImpl.getInStockList();
            model.addAttribute("categoriesList", categoryServiceImpl.getCategoriesMap());
            model.addAttribute("inStockList", inStockList);
            List<String> uploadImages = productServiceImpl.productImageUpload(product);
            product.setImagesList(uploadImages);
            categoryServiceImpl.addProduct(product);
            model.addAttribute("addedProduct", true);
            return "admin";
        }
    }

    @RequestMapping(value = "/admproductedit", method = RequestMethod.GET)
    public String editProduct(Model model){
        LinkedHashMap<String, String> categoryList = categoryServiceImpl.getCategoriesMap();
        List<Product> fullProductsList = productServiceImpl.getAllProducts();
        model.addAttribute("allProducts", fullProductsList);
        model.addAttribute("categoryList", categoryList);
        return "admproductedit";
    }

    @RequestMapping(value = "/edit-product/{id}", method = RequestMethod.GET)
    public ModelAndView editProduct(@PathVariable String id, Model model){
        Product product = productServiceImpl.getProductById(id);
        LinkedHashMap<String, String> categoryList = categoryServiceImpl.getCategoriesMap();
        LinkedHashMap<String, String> inStockList = productServiceImpl.getInStockList();
        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("inStockList", inStockList);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productedit", product);
        modelAndView.setViewName("admseparateprodedit");
        return modelAndView;
    }

    @RequestMapping(value = "/edit-product/{id}", method = RequestMethod.POST)
    public String editProductResult(@ModelAttribute ("productedit") @Validated Product product, BindingResult result, Model model){
        if (result.hasErrors()){
            LinkedHashMap<String, String> categoryList = categoryServiceImpl.getCategoriesMap();
            LinkedHashMap<String, String> inStockList = productServiceImpl.getInStockList();
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("inStockList", inStockList);
            return "admseparateprodedit";
        } else {
            productServiceImpl.updateProduct(product);
            model.addAttribute("updated", "Product was successful updated!");
            LinkedHashMap<String, String> categoryList = categoryServiceImpl.getCategoriesMap();
            LinkedHashMap<String, String> inStockList = productServiceImpl.getInStockList();
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("inStockList", inStockList);
            return "admseparateprodedit";
        }
    }

    @RequestMapping (value = "/deleteproduct/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteProduct(@PathVariable String id){
        productServiceImpl.deleteProduct(id);
        return "admproductedit";
    }
}