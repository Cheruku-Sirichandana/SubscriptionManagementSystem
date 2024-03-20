package com.SpringBoot.SubscriptionManagementSystemProject.Validations;

import com.SpringBoot.SubscriptionManagementSystemProject.Model.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class UserValidations implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserModel userModel = (UserModel) target;
        int userId = 0;

        String userIdStr = String.valueOf(userId);
        if (userIdStr != null ) {
            try {
                userId = Integer.parseInt(String.valueOf(userId));
            } catch (NumberFormatException e) {
                errors.rejectValue("userId","userId");
            }
        }


         ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName");
        if (userModel.getUserEmail() != null&& !userModel.getUserEmail().endsWith("@gmail.com")){
            errors.rejectValue("userEmail","userEmail");
        }
        String userPassword=userModel.getUserPassword();
        if(userPassword!=null && userPassword.length()<6)
        {
            errors.rejectValue("userPassword", "userPassword");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"subscriptionStatus","subscriptionStatus");




    }
    public void validate1(Object target, Errors errors) {


        UserModel userModel = (UserModel) target;


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "userId");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName");
//        if (userModel.getUserEmail() != null&& !userModel.getUserEmail().endsWith("@gmail.com")){
//            errors.rejectValue("userEmail","userEmail");
//        }
        String userPassword=userModel.getUserPassword();
        if(userPassword!=null && userPassword.length()<6)
        {
            errors.rejectValue("userPassword", "userPassword");
        }

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"subscriptionStatus","subscriptionStatus");
    }
}
