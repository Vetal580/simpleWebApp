package com.springapp.controllers;

import com.springapp.model.Product;
import com.springapp.service.CartServiceImpl;
import com.springapp.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class CartController {
    @Autowired
    CartServiceImpl cartServiceImpl;
    @Autowired
    ProductServiceImpl productServiceImpl;

    @RequestMapping (value = "/cart", method = RequestMethod.GET)
    public String cartPage(Model model){
        model.addAttribute("productList", cartServiceImpl.getProductList());
        return "cart";
    }

    @RequestMapping (value = "/remove-product/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String removeProductFromCart(@PathVariable (value = "id") String id){
        int productId = Integer.parseInt(id);
        ArrayList<Product> productCartList = (ArrayList<Product>) cartServiceImpl.getProductList();
        for (Product p : productCartList){
            if (p.getId() == productId) productCartList.remove(p);
        }
        cartServiceImpl.setProductList(productCartList);
        return "cart";
    }
}
