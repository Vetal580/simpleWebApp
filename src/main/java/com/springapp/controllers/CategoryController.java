package com.springapp.controllers;

import com.springapp.model.Category;
import com.springapp.service.CategoryServiceImpl;
import com.springapp.validation.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired CategoryServiceImpl categoryServiceImpl;
    @Autowired CategoryValidator categoryValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(categoryValidator);
    }

    @RequestMapping(value = "/admcategoryedit", method = RequestMethod.GET)
    public ModelAndView categoryEdit(Model model){
        List<Category> categoryList = categoryServiceImpl.getCategoryList();
        model.addAttribute("categoryList", categoryList);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("category", new Category());
        modelAndView.setViewName("admcategoryedit");
        return modelAndView;
    }

    @RequestMapping (value = "/admcategoryedit", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("category") @Validated Category category, BindingResult result, Model model){
        if (result.hasErrors()){
            List<Category> categoryList = categoryServiceImpl.getCategoryList();
            model.addAttribute("categoryList", categoryList);
            return "admcategoryedit";
        } else {
            categoryServiceImpl.addCategory(category);
            List<Category> categoryList = categoryServiceImpl.getCategoryList();
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("categoryAdded", "New Category Successful added!");
            return "admcategoryedit";
        }
    }

    @RequestMapping(value = "/edit-category/{id}", method = RequestMethod.GET)
    public ModelAndView editCategory(@PathVariable String id){
        Category category = categoryServiceImpl.getCategoryById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("category", category);
        modelAndView.setViewName("admseparatecatedit");
        return modelAndView;
    }

    @RequestMapping(value = "/edit-category/{id}", method = RequestMethod.POST)
    public String editCategoryResult(@ModelAttribute ("category") @Validated Category category, BindingResult result, Model model){
        if (result.hasErrors()){
            return "admseparatecatedit";
        } else {
            categoryServiceImpl.updateCategory(category);
            model.addAttribute("updated", "Category was successful updated!");
            return "admseparatecatedit";
        }
    }

    @RequestMapping (value = "/deletecategory/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteCategory(@PathVariable String id){
        categoryServiceImpl.deleteCategory(id);
        return "admcategoryedit";
    }
}
