package com.SpringBoot.SubscriptionManagementSystemProject.Model;

import com.SpringBoot.SubscriptionManagementSystemProject.Entity.SubscriptionPlan;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;

@Component

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @NotNull(message = "User ID should not be null")
    @NotBlank(message = "Id is required")
    @Pattern(regexp = "\\d$",message = "User ID should be an integer")
    private int userId;

    private String mobileNo;

    private String userName;
    private String userEmail;
    private String userPassword;

    private SubscriptionStatus subscriptionStatus;
private SubscriptionPlan subscriptionPlan;
private String role;
}
