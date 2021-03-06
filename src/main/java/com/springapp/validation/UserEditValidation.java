package com.springapp.validation;

import com.springapp.model.User;
import com.springapp.service.UserService;
import com.springapp.service.UserServiceImpl;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Repository
public class UserEditValidation implements Validator {
    @Autowired UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (user.getUsername().isEmpty() || user.getUsername().length() < 4 || user.getUsername().length() > 30) {
            errors.rejectValue("username", "user.login.correct.error");
        }


        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            errors.rejectValue("email", "user.email.correct.error");
        }

        if (!phoneValidation(user.getPhone())) {
            errors.rejectValue("phone", "user.phone.correct.error");
        }
    }

    private boolean phoneValidation(String number){
        boolean result = false;
        String regExp = "^380(\\d+){9}$";

        String num = number.replaceAll("[-+)(]","");
        if (num.matches("^(\\d+)$")){
            if (num.length()<12 || num.length()>12 ) result = false;
            if (num.matches(regExp)) result = true;
        }
        return result;
    }
}
