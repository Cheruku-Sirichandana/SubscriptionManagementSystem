package com.SpringBoot.SubscriptionManagementSystemProject.Entity;

import com.SpringBoot.SubscriptionManagementSystemProject.Model.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Users {
    @Id
    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;

    private String role;

    private SubscriptionStatus subscriptionStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    private SubscriptionPlan subscriptionPlan;


}
