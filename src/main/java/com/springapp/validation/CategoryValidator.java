package com.springapp.validation;

import com.springapp.model.Category;
import com.springapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Repository
public class CategoryValidator implements Validator {
    @Autowired CategoryService categoryService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Category.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Category category = (Category) o;

        if (category.getCategoryName().length() > 50){
            errors.rejectValue("categoryName", "category.too.long.name");
        }

        if (category.getCategoryName().isEmpty()){
            errors.rejectValue("categoryName", "category.name.empty.error");
        }

        if (categoryService.categoryValidation(category.getCategoryName()) == 1){
            errors.rejectValue("categoryName", "category.name.unique.error");
        }
    }
}
