package com.SpringBoot.SubscriptionManagementSystemProject.Model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AdminModel {

    private int adminId;
    private String adminUsername;
    private String adminPassword;
    private String role;

}

