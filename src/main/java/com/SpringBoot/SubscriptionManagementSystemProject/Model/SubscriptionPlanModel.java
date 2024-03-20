package com.SpringBoot.SubscriptionManagementSystemProject.Model;

import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Content;
import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class SubscriptionPlanModel {
    private int planId;
    private String planName;
    private double planPrice;
    private int planDuration;
    private String planFeatures;
    private List<Content> contentList=new ArrayList<>();
private List<Users> users;

    @Override
    public String toString() {
        return "SubscriptionPlanModel{" +
                "planId=" + planId +
                ", planName='" + planName + '\'' +
                ", planPrice=" + planPrice +
                ", planDuration=" + planDuration +
                ", planFeatures='" + planFeatures + '\'' +
                ", contentList=" + contentList +
                ", users=" + users +
                '}';
    }
}
