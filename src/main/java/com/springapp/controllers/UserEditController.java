package com.springapp.controllers;

import com.springapp.model.User;
import com.springapp.service.UserServiceImpl;
import com.springapp.validation.UserEditValidation;
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
public class UserEditController {
    @Autowired UserServiceImpl userServiceImpl;
    @Autowired UserEditValidation userEditValidation;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userEditValidation);
    }

    @RequestMapping(value = "/admuseredit", method = RequestMethod.GET)
    public String editUsers(Model model){
        List<User> usersList = userServiceImpl.getAllUsers();
        model.addAttribute("usersList", usersList);
        return "admuseredit";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable String id){
        User user = userServiceImpl.findUserByUsername(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("useredit", user);
        modelAndView.setViewName("admseparateuseredit");
        return modelAndView;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public String editUserResult(@ModelAttribute("useredit") @Validated User user, BindingResult result, Model model){
        if (result.hasErrors()){
            return "admseparateuseredit";
        } else {
            userServiceImpl.updateUser(user);
            model.addAttribute("updated", "User was successful updated!");
            return "admseparateuseredit";
        }
    }

    @RequestMapping (value = "/deleteuser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteUser(@PathVariable String id){
        userServiceImpl.deleteUser(id);
        return "admuseredit";
    }
}
