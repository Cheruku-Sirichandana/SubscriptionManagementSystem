package com.SpringBoot.SubscriptionManagementSystemProject.Repository;

import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content,Integer> {
    List<Content> findBySubscriptionLevel(String subscriptionLevel);

}
