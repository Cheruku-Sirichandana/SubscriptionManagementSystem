package com.SpringBoot.SubscriptionManagementSystemProject.Repository;

import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
     Boolean existsByAdminUsername(String adminUsername);

    Admin findByAdminUsername(String adminUsername);
}
