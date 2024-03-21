package com.SpringBoot.SubscriptionManagementSystemProject.Model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PaymentModel {
    private int paymentId;
    private Double amount;

    private LocalDateTime paymentDate;
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = now.format(formatter);
    private String paymentMethod;
    private String paymentStatus;



}
