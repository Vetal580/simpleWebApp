package com.springapp.controllers;

import com.springapp.model.Category;
import com.springapp.model.Product;
import com.springapp.model.User;
import com.springapp.service.CategoryServiceImpl;
import com.springapp.service.ProductServiceImpl;
import com.springapp.service.UserServiceImpl;
import com.springapp.validation.UserRegistrationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class UserController {
    @Autowired UserServiceImpl userServiceImpl;
    @Autowired UserRegistrationValidation userValidator;
    @Autowired CategoryServiceImpl categoryServiceImpl;
    @Autowired ProductServiceImpl productServiceImpl;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registerUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("registration", new User());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String userCheck(@ModelAttribute("registration") @Validated User user, BindingResult result, Model model){
        if (result.hasErrors()){
            return "registration";
        } else {
            userServiceImpl.addUser(user);
            model.addAttribute("regSuc", "Registration Success! Please Log-In!");
            return "registration";
        }
    }

    @RequestMapping(value = "/")
    public ModelAndView homepage(Model model){
        ModelAndView view = new ModelAndView();
        ArrayList<Category> categoryList = (ArrayList<Category>) categoryServiceImpl.getCategoryList();
        ArrayList<Product> products = (ArrayList<Product>) productServiceImpl.getAllProducts();
        model.addAttribute("categories", categoryList);
        model.addAttribute("product", products);
        view.setViewName("home");
        return view;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUser(Model model){
        return "login";
    }

}