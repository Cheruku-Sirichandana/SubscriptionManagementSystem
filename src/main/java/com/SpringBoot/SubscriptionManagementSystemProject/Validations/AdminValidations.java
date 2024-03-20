package com.SpringBoot.SubscriptionManagementSystemProject.Validations;

import com.SpringBoot.SubscriptionManagementSystemProject.Model.AdminModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AdminValidations implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AdminModel.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("THis is the error page");

        AdminModel adminModel= (AdminModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"adminId","adminId");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"adminUsername","adminUsername");

        String adminPassword=adminModel.getAdminPassword();
        if(adminPassword!=null && adminPassword.length()<6)
        {
            errors.rejectValue("adminPassword", "adminPassword");
        }


    }
}
