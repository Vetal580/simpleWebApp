package com.springapp.controllers;

import com.springapp.model.Category;
import com.springapp.model.Product;
import com.springapp.model.Search;
import com.springapp.service.CategoryService;
import com.springapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    @Autowired ProductService productService;
    @Autowired CategoryService categoryService;
    @Autowired Search search;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@ModelAttribute("search") Search search, RedirectAttributes redirect){
        redirect.addFlashAttribute("searchTerm", search.getSearchRow());
        return "redirect:search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String showSearchResult(@ModelAttribute("searchTerm") String searchTerm, Model model){
        ArrayList<Category> categoryList = (ArrayList<Category>) categoryService.getCategoryList();
        List<Product> productFindList = productService.searchProduct(searchTerm);
        model.addAttribute("search", search);
        if (searchTerm.equals("") || productFindList.isEmpty()){
            model.addAttribute("noMatches", "No matches found! Try again!");
            model.addAttribute("categories", categoryList);
            return "search";
        } else {
            model.addAttribute("searchResult", productFindList);
            model.addAttribute("categories", categoryList);
            return "search";
        }
    }
}
