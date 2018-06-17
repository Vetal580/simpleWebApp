package com.springapp.controllers;

import com.springapp.model.Product;
import com.springapp.model.Search;
import com.springapp.service.CartService;
import com.springapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class CartController {
    @Autowired CartService cartService;
    @Autowired ProductService productService;
    @Autowired Search search;

    @RequestMapping (value = "/cart", method = RequestMethod.GET)
    public String cartPage(Model model){
        model.addAttribute("productList", cartService.getProductList());
        model.addAttribute("search", search);
        return "cart";
    }

    @RequestMapping (value = "/remove-product/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String removeProductFromCart(@PathVariable (value = "id") String id){
        int productId = Integer.parseInt(id);
        ArrayList<Product> productCartList = (ArrayList<Product>) cartService.getProductList();
        for (Product p : productCartList){
            if (p.getId() == productId) productCartList.remove(p);
        }
        cartService.setProductList(productCartList);
        return "cart";
    }
}
