package com.SpringBoot.SubscriptionManagementSystemProject.Conversions;

import com.SpringBoot.SubscriptionManagementSystemProject.Entity.*;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Model_Entity {
    @Autowired
        PasswordEncoder passwordEncoder;


        public Users userModel_Entity(UserModel userModel){
            Users users = new Users();
            users.setUserName(userModel.getUserName());
            users.setUserPassword(passwordEncoder.encode(userModel.getUserPassword()));
            users.setUserId(userModel.getUserId());
            users.setUserEmail(userModel.getUserEmail());
            users.setRole("ROLE_USER");
            return users;
        }

        public Admin adminModel_Entity(AdminModel adminModel){
            Admin admin = new Admin();
            admin.setAdminUsername(adminModel.getAdminUsername());
            admin.setAdminPassword(passwordEncoder.encode(adminModel.getAdminPassword()));
            admin.setAdminId(adminModel.getAdminId());

            admin.setRole(adminModel.getRole());
            return admin;
        }

        public Content contentModel_Entity(ContentModel contentModel){
            Content content = new Content();
            content.setContentId(contentModel.getContentId());
            content.setType(contentModel.getType());
            content.setTitle(contentModel.getTitle());
            content.setDescription(contentModel.getDescription());
            content.setCategory(contentModel.getCategory());
            content.setDurationInMinutes(contentModel.getDurationInMinutes());
            content.setSubscriptionLevel(contentModel.getSubscriptionLevel());
            //content.setSubscriptionPlanList(contentModel.getSubscriptionPlanList());
            return content;
        }

        public Payment paymentModel_Entity(PaymentModel paymentModel){
            Payment payment = new Payment();
            payment.setPaymentId(paymentModel.getPaymentId());
            payment.setAmount(paymentModel.getAmount());
            payment.setPaymentDate(paymentModel.getPaymentDate());
            payment.setPaymentMethod(paymentModel.getPaymentMethod());
            payment.setPaymentStatus(paymentModel.getPaymentStatus());
            return payment;
        }

        public SubscriptionPlan subscriptionPlanModel_entity(SubscriptionPlanModel subscriptionPlanModel){
            SubscriptionPlan subscriptionPlan = new SubscriptionPlan();
            subscriptionPlan.setPlanId(subscriptionPlanModel.getPlanId());
            subscriptionPlan.setPlanName(subscriptionPlanModel.getPlanName());
            subscriptionPlan.setPlanPrice(subscriptionPlanModel.getPlanPrice());
            subscriptionPlan.setPlanDuration(subscriptionPlanModel.getPlanDuration());
            subscriptionPlan.setPlanFeatures(subscriptionPlanModel.getPlanFeatures());
           subscriptionPlan.setContentList(subscriptionPlanModel.getContentList());
           subscriptionPlan.setUsers(subscriptionPlanModel.getUsers());
            return subscriptionPlan;
        }




}
