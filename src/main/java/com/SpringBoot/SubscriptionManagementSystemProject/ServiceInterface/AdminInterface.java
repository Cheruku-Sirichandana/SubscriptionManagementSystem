package com.SpringBoot.SubscriptionManagementSystemProject.ServiceInterface;

import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Admin;
import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Content;
import com.SpringBoot.SubscriptionManagementSystemProject.Entity.SubscriptionPlan;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.AdminModel;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.ContentModel;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.SubscriptionPlanModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminInterface {
    public Admin adminRegistered(AdminModel adminModel);
    public Boolean adminCheck(AdminModel adminModel);
    public Content addedContent(ContentModel contentModel);
    public String deleteContent(int contentId);
    public String deleteSubscriptionPlan(int planId);
    public String updatedContent(ContentModel contentModel,int contentId);
    public List<ContentModel> viewContent();
    public SubscriptionPlanModel addedSubscriptionPlan(SubscriptionPlanModel subscriptionPlanModel);
    public List<SubscriptionPlanModel> viewSubscriptionPlans();
    public String deleteSubscription(int planId);
    public SubscriptionPlan updatedSubscriptionPlans(SubscriptionPlan subscriptionPlan,int planId);
    public SubscriptionPlan searchByPlanId(int planId);
    public ContentModel searchByContentId(int contentId);

}
