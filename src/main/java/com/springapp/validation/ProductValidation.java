package com.springapp.validation;

import com.springapp.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Repository
public class ProductValidation implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Product product = (Product) o;

        if (product.getName().isEmpty()){
            errors.rejectValue("name", "product.empty.productname");
        }

        if (product.getPrice().isEmpty() || !product.getPrice().matches("([0-9]*\\.([0-9]){2})")){
            errors.rejectValue("price", "product.incorrect.price");
        }
    }
}
