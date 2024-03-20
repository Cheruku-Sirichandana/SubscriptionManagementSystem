package com.SpringBoot.SubscriptionManagementSystemProject.Validations;

import com.SpringBoot.SubscriptionManagementSystemProject.Model.SubscriptionPlanModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SubscriptionPlanValidations implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SubscriptionPlanModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SubscriptionPlanModel subscriptionPlanModel = (SubscriptionPlanModel) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "planId", "planId","please enter price");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "planName", "planName");


        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"planPrice","planPrice");
        if (subscriptionPlanModel.getPlanPrice() <= 0) {
            errors.rejectValue("planPrice", "planPrice");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "planDuration", "planDuration");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "planFeatures", "planFeatures");
    }
}
