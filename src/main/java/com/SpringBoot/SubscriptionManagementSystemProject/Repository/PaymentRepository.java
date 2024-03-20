package com.SpringBoot.SubscriptionManagementSystemProject.Repository;

import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
