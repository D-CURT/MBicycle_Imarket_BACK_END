package com.mbicycle.imarket.services.securities;

import com.mbicycle.imarket.beans.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



@Component
@PropertySource("classpath:validation.properties")
public class UserValidator implements Validator {

    @Autowired
    private com.mbicycle.imarket.services.UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");
        if (user.getLogin().length() < 5 || user.getLogin().length() > 32) {
            errors.rejectValue("login", "Size.userForm.username");
        }

        if (userService.get(user.getLogin()) != null) {
            errors.rejectValue("login", "Duplicate.userForm.username");
        }


        String str = new BCryptPasswordEncoder().encode(user.getPassword());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 5 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        //if (!user.getConfirmPassword().equals(user.getPassword())) ;
        //    errors.rejectValue("confirmPassword", "Different.userForm.password");
        //}
    }
}
