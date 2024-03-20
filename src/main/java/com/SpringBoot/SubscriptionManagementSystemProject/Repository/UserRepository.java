package com.SpringBoot.SubscriptionManagementSystemProject.Repository;

import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Admin;
import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    Boolean existsByUserName(String userName);

    Users findByUserName(String userName);
}
