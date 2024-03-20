package com.SpringBoot.SubscriptionManagementSystemProject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor

@Setter
@Getter
public class Subscriber {
    @Id
    @GeneratedValue
    private int subscriberId;
    private String subscriberName;
    private String subscriberEmail;
    private Long subscriberPhoneNumber;


}
//Subscriber:
//
//Attributes: ID, name, email, phone number, address
//Subscription:
//
//Attributes: ID, subscriber ID (foreign key), subscription plan ID (foreign key), start date, end date, status (active, expired, canceled, on hold)
//Subscription Plan:
//
//Attributes: ID, name, description, price, duration (e.g., monthly, yearly), features
//Payment:
//
//Attributes: ID, subscriber ID (foreign key), amount, payment date, payment method (e.g., credit card, PayPal), status (success, pending, failed)
//Notification:
//
//Attributes: ID, subscriber ID (foreign key), message, timestamp, status (delivered, pending, failed)
//Admin/User:
//
//Attributes: ID, username, password, role (admin, customer support, etc.)
//Billing Cycle:
//
//Attributes: ID, start date, end date, frequency (e.g., monthly, quarterly)
//Promotion/Coupon:
//
//Attributes: ID, code, description, discount percentage, expiry date, terms
