package com.SpringBoot.SubscriptionManagementSystemProject.Entity;

import com.SpringBoot.SubscriptionManagementSystemProject.Model.ContentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor

@Setter
@Getter
public class Content {
    @Id
    @NotNull(message = "content Id should not be null")
    @NotBlank(message = "content ID should not be blank")
    private int contentId;
    private String title;
    private String description;
    private ContentType type;
    private String category;
    private int durationInMinutes;
    private String subscriptionLevel;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<SubscriptionPlan> subscriptionPlanList=new ArrayList<>();


}
