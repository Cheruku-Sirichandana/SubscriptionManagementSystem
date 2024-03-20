package com.SpringBoot.SubscriptionManagementSystemProject.Repository;

import com.SpringBoot.SubscriptionManagementSystemProject.Entity.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan,Integer> {
}
