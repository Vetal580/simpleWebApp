package com.springapp.controllers;

import com.springapp.model.Category;
import com.springapp.model.Product;
import com.springapp.model.Search;
import com.springapp.model.User;
import com.springapp.service.*;
import com.springapp.validation.UserRegistrationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class UserController {
    @Autowired UserService userService;
    @Autowired UserRegistrationValidation userValidator;
    @Autowired CategoryService categoryService;
    @Autowired ProductService productService;
    @Autowired Search search;

    @InitBinder (value = "registration")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerUser(Model model){
        model.addAttribute("search", search);
        model.addAttribute("registration", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String userCheck(@ModelAttribute("registration") @Validated User user, BindingResult result, Model model){
        model.addAttribute("search", search);
        if (result.hasErrors()){
            return "registration";
        } else {
            userService.addUser(user);
            model.addAttribute("regSuc", "Registration Success! Please Log-In!");
            return "registration";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homepage(Model model){
        ArrayList<Category> categoryList = (ArrayList<Category>) categoryService.getCategoryList();
        ArrayList<Product> products = (ArrayList<Product>) productService.getAllProducts();
        model.addAttribute("categories", categoryList);
        model.addAttribute("product", products);
        model.addAttribute("search", search);
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUser(Model model){
        model.addAttribute("search", search);
        return "login";
    }

}