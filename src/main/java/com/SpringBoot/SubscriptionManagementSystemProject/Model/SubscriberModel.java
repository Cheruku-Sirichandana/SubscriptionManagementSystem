package com.SpringBoot.SubscriptionManagementSystemProject.Model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class SubscriberModel {
    private int subscriberId;
    private String subscriberName;
    private String subscriberEmail;
    private Long subscriberPhoneNumber;
}
