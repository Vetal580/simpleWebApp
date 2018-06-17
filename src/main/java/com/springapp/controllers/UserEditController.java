package com.springapp.controllers;

import com.springapp.model.Search;
import com.springapp.model.User;
import com.springapp.service.UserService;
import com.springapp.service.UserServiceImpl;
import com.springapp.validation.UserEditValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserEditController {
    @Autowired UserService userService;
    @Autowired UserEditValidation userEditValidation;
    @Autowired Search search;

    @InitBinder (value = "useredit")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userEditValidation);
    }

    @RequestMapping(value = "/admuseredit", method = RequestMethod.GET)
    public String editUsers(Model model){
        List<User> usersList = userService.getAllUsers();
        model.addAttribute("usersList", usersList);
        model.addAttribute("search", search);
        return "admuseredit";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable String id, Model model){
        User user = userService.findUserByUsername(id);
        model.addAttribute("useredit", user);
        model.addAttribute("search", search);
        return "admseparateuseredit";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public String editUserResult(@ModelAttribute("useredit") @Validated User user, BindingResult result, Model model){
        model.addAttribute("search", search);
        if (result.hasErrors()){
            return "admseparateuseredit";
        } else {
            userService.updateUser(user);
            model.addAttribute("updated", "User was successful updated!");
            return "admseparateuseredit";
        }
    }

    @RequestMapping (value = "/deleteuser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return "admuseredit";
    }
}
