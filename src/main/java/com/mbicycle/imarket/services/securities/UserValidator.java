package com.mbicycle.imarket.services.securities;

import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



@Component
public class UserValidator implements Validator {

    @Autowired
    private com.mbicycle.imarket.services.UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getLogin().length() < 8 || user.getLogin().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByLogin(user.getLogin()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        //if (!user.getConfirmPassword().equals(user.getPassword())) ;
        if (!user.getPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
