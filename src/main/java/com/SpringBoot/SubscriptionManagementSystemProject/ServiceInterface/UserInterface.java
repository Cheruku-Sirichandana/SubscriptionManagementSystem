package com.SpringBoot.SubscriptionManagementSystemProject.ServiceInterface;

import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Content;
import com.SpringBoot.SubscriptionManagementSystemProject.Entity.SubscriptionPlan;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.PaymentModel;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.SubscriptionPlanModel;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.SubscriptionStatus;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserInterface {
    public UserModel userRegistered(UserModel userModel);
    public List<UserModel> viewUsers();
    public Boolean userCheck(UserModel userModel);
    public SubscriptionStatus subscriptionStatus(UserModel userModel);
    public SubscriptionStatus updatedStatus(UserModel userModel);
    public List<Content> display(int userId, int planId, SubscriptionStatus subscriptionStatus);
    public SubscriptionStatus setSubscriptionStatus(int userId,int planId);
    public int planId(int userId);
    public String setPaymentStatus(String paymentStatus);
    public PaymentModel save(PaymentModel paymentModel);
    public List<Content> subscriptionContentForUser(int userId);
    public List<SubscriptionPlan> upgradeSubscription(int userId, String planName);
    public String findPlanName(SubscriptionPlanModel subscriptionPlanModel);
    public String removeSubscriptionPlan(int userId, int planId);
    public Integer getPlanId(int userId);
    public List<SubscriptionPlan> degradeSubscription(int userId,String planName);
}
