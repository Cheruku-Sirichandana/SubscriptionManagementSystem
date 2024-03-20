package com.SpringBoot.SubscriptionManagementSystemProject.Conversions;

import com.SpringBoot.SubscriptionManagementSystemProject.Entity.*;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.*;
import org.springframework.stereotype.Component;

@Component
public class Entity_Model {
    public UserModel userEntity_Model(Users userEntity) {
        UserModel userModel = new UserModel();
        userModel.setUserName(userEntity.getUserName());
        userModel.setUserPassword(userEntity.getUserPassword());
        userModel.setUserEmail(userEntity.getUserEmail());
        userModel.setUserPassword(userEntity.getUserPassword());
        userModel.setRole(userEntity.getRole());
        return userModel;
    }

    public AdminModel adminEntity_Model(Admin adminEntity){
        AdminModel adminModel = new AdminModel();
        adminModel.setAdminUsername(adminEntity.getAdminUsername());
        adminModel.setAdminPassword(adminEntity.getAdminPassword());
        adminModel.setAdminId(adminEntity.getAdminId());

        adminModel.setRole(adminEntity.getRole());
        return adminModel;
    }

        public ContentModel contentEntity_Model(Content contentEntity){
            ContentModel contentModel = new ContentModel();
            contentModel.setContentId(contentEntity.getContentId());
            contentModel.setTitle(contentEntity.getTitle());
            contentModel.setDescription(contentEntity.getDescription());
            contentModel.setType(contentEntity.getType());
            contentModel.setCategory(contentEntity.getCategory());
            contentModel.setDurationInMinutes(contentEntity.getDurationInMinutes());
            contentModel.setSubscriptionLevel(contentEntity.getSubscriptionLevel());
            return contentModel;
        }
        public PaymentModel paymentEntity_model(Payment movieEntity){
            PaymentModel paymentModel = new PaymentModel();
            paymentModel.setPaymentId(movieEntity.getPaymentId());
            paymentModel.setAmount(movieEntity.getAmount());
            paymentModel.setPaymentDate(movieEntity.getPaymentDate());
            paymentModel.setPaymentMethod(movieEntity.getPaymentMethod());
            paymentModel.setPaymentStatus(movieEntity.getPaymentStatus());
            return paymentModel;
        }

        public SubscriptionPlanModel subscriptionPlanModel(SubscriptionPlan subscriptionPlan){
            SubscriptionPlanModel subscriptionPlanModel = new SubscriptionPlanModel();
            subscriptionPlanModel.setPlanId(subscriptionPlan.getPlanId());
            subscriptionPlanModel.setPlanName(subscriptionPlan.getPlanName());
            subscriptionPlanModel.setPlanPrice(subscriptionPlan.getPlanPrice());
            subscriptionPlanModel.setPlanDuration(subscriptionPlan.getPlanDuration());
            subscriptionPlanModel.setPlanFeatures(subscriptionPlan.getPlanFeatures());
            subscriptionPlanModel.setContentList(subscriptionPlan.getContentList());
            subscriptionPlanModel.setUsers(subscriptionPlan.getUsers());
            return subscriptionPlanModel;
        }







}
