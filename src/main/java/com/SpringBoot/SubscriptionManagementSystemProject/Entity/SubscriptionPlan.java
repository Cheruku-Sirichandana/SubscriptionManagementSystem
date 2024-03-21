package com.SpringBoot.SubscriptionManagementSystemProject.Entity;

import com.SpringBoot.SubscriptionManagementSystemProject.Model.SubscriptionPlanModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class SubscriptionPlan {

    @Id
    private int planId;
    private String planName;

    private double planPrice;

    private int planDuration;
    private String planFeatures;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Content> contentList=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Users> users=new ArrayList<>();

    @Override
    public String toString() {
        return "SubscriptionPlan{" +
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
