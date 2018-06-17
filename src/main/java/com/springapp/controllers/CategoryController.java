package com.springapp.controllers;

import com.springapp.model.Category;
import com.springapp.model.Search;
import com.springapp.service.CategoryService;
import com.springapp.validation.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired CategoryService categoryService;
    @Autowired CategoryValidator categoryValidator;
    @Autowired Search search;

    @InitBinder (value = "category")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(categoryValidator);
    }

    @RequestMapping(value = "/admcategoryedit", method = RequestMethod.GET)
    public String categoryEdit(Model model){
        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("category", new Category());
        model.addAttribute("search", search);
        return "admcategoryedit";
    }

    @RequestMapping (value = "/admcategoryedit", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("category") @Validated Category category, BindingResult result, Model model){
        model.addAttribute("search", search);
        if (result.hasErrors()){
            List<Category> categoryList = categoryService.getCategoryList();
            model.addAttribute("categoryList", categoryList);
            return "admcategoryedit";
        } else {
            categoryService.addCategory(category);
            List<Category> categoryList = categoryService.getCategoryList();
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("categoryAdded", "New Category Successful added!");
            return "admcategoryedit";
        }
    }

    @RequestMapping(value = "/edit-category/{id}", method = RequestMethod.GET)
    public String editCategory(@PathVariable String id, Model model){
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        model.addAttribute("search", search);
        return "admseparatecatedit";
    }

    @RequestMapping(value = "/edit-category/{id}", method = RequestMethod.POST)
    public String editCategoryResult(@ModelAttribute ("category") @Validated Category category, BindingResult result, Model model){
        model.addAttribute("search", search);
        if (result.hasErrors()){
            return "admseparatecatedit";
        } else {
            categoryService.updateCategory(category);
            model.addAttribute("updated", "Category was successful updated!");
            return "admseparatecatedit";
        }
    }

    @RequestMapping (value = "/deletecategory/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteCategory(@PathVariable String id){
        categoryService.deleteCategory(id);
        return "admcategoryedit";
    }
}
