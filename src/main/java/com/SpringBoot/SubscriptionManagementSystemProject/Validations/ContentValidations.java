package com.SpringBoot.SubscriptionManagementSystemProject.Validations;

import com.SpringBoot.SubscriptionManagementSystemProject.Model.ContentModel;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ContentValidations implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ContentModel.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        ContentModel contentModel = (ContentModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contentId", "contentId");

        String title=contentModel.getTitle();
        if (title != null && title.trim().isEmpty()) {
            errors.rejectValue("title", "title.empty", "Title cannot be empty");
        } else if (title != null && title.length() >50) {
            errors.rejectValue("title", "title.toolong", "Title must be less than  50  characters");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description","description");
        if (contentModel.getDurationInMinutes() <= 0) {
            errors.rejectValue("durationInMinutes", "negative.duration", "Duration must be a non-negative number.");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"subscriptionLevel","subscriptionLevel");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"category","category");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"type","xyz");
    }
}
