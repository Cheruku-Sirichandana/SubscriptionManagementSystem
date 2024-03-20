package com.SpringBoot.SubscriptionManagementSystemProject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Payment {
    //ID, subscriber ID (foreign key), amount, payment date, payment method (e.g., credit card, PayPal),
    // status (success, pending, failed)
    @Id
    private int paymentId;
    //foreign key,subscriberId
    private Double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;//credit card,paypal,gpay
    private String paymentStatus;//success,pending,failed

}
