package com.SpringBoot.SubscriptionManagementSystemProject.Service;

import com.SpringBoot.SubscriptionManagementSystemProject.Conversions.Entity_Model;
import com.SpringBoot.SubscriptionManagementSystemProject.Conversions.Model_Entity;
import com.SpringBoot.SubscriptionManagementSystemProject.Entity.*;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.AdminModel;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.ContentModel;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.SubscriptionPlanModel;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.SubscriptionStatus;
import com.SpringBoot.SubscriptionManagementSystemProject.Repository.AdminRepository;
import com.SpringBoot.SubscriptionManagementSystemProject.Repository.ContentRepository;
import com.SpringBoot.SubscriptionManagementSystemProject.Repository.SubscriptionPlanRepository;
import com.SpringBoot.SubscriptionManagementSystemProject.Repository.UserRepository;
import com.SpringBoot.SubscriptionManagementSystemProject.ServiceInterface.AdminInterface;
import org.aspectj.runtime.internal.Conversions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AdminServices implements AdminInterface {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
   @Autowired
   private Entity_Model entityModel;
   @Autowired
   private Model_Entity modelEntity;
   public Admin adminRegistered(AdminModel adminModel){
       Admin admin=adminRepository.findByAdminUsername(adminModel.getAdminUsername());
       if(admin==null){
           Admin admin1=  modelEntity.adminModel_Entity(adminModel);


          // BeanUtils.copyProperties(adminModel,admin1);
           admin1.setRole("ROLE_ADMIN");

           admin1.setAdminUsername(adminModel.getAdminUsername());
           admin1.setAdminPassword(passwordEncoder.encode(adminModel.getAdminPassword()));
           adminRepository.save(admin1);
          return admin1;

       }
       return null;
   }
   public Boolean adminCheck(AdminModel adminModel){
       Admin admin=adminRepository.findByAdminUsername(adminModel.getAdminUsername());

           if(admin!=null&&passwordEncoder.matches(adminModel.getAdminPassword(),admin.getAdminPassword())){
           {
               return true;
           }
       }
       return false;
   }
   public Content addedContent(ContentModel contentModel){
       Content content=contentRepository.findById(contentModel.getContentId()).orElse(null);
       if(content==null){
           Content content1=  modelEntity.contentModel_Entity(contentModel);

          // BeanUtils.copyProperties(contentModel,content1);
           contentRepository.save(content1);
           return null;
       }
       return content;
   }
     public String deleteContent(int contentId){
           System.out.println("Entered deleted Content");
           Content content=contentRepository.getReferenceById(contentId);
           List<SubscriptionPlan> subscriptionPlanList=content.getSubscriptionPlanList();
           subscriptionPlanList.forEach(subscriptionPlan -> {
           List<Content> contentList=subscriptionPlan.getContentList();
           if(contentList.contains(content)){
              contentList.remove(content);
              subscriptionPlan.setContentList(contentList);
              subscriptionPlanRepository.save(subscriptionPlan);
           }
           });

           content.setSubscriptionPlanList(new ArrayList<>());
           contentRepository.delete(content);
           return "Deleted Successfully";
       }
   public String deleteSubscriptionPlan(int planId) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(planId).orElse(null);
        if (subscriptionPlan == null) {
            return "Subscription plan not found";
        }

        List<Content> contentList = subscriptionPlan.getContentList();
        List<Users> users = userRepository.findAll();

        contentList.forEach(content -> {
        List<SubscriptionPlan> subscriptionPlanList =new ArrayList<>(content.getSubscriptionPlanList()) ;

        subscriptionPlanList.remove(subscriptionPlan);
        content.setSubscriptionPlanList(subscriptionPlanList);
        contentRepository.save(content);
        });
        boolean bool = users.stream().filter(user -> user.getSubscriptionPlan().getPlanId() == planId).anyMatch(user -> user.getSubscriptionStatus().equals(SubscriptionStatus.ACTIVE));
        if(bool){
          return "msg";
        }
        subscriptionPlan.setContentList(new ArrayList<>());
        subscriptionPlanRepository.delete(subscriptionPlan);
        return "deleted";
      }
      public String updatedContent(ContentModel contentModel,int contentId){
        Content content=contentRepository.findById(contentId).orElse(null);
        if(content!=null){
           content.setTitle(contentModel.getTitle());
           content.setCategory(contentModel.getCategory());
           content.setDescription(contentModel.getDescription());
           content.setSubscriptionLevel(contentModel.getSubscriptionLevel());
           content.setDurationInMinutes(contentModel.getDurationInMinutes());
           content.setType(contentModel.getType());
           contentRepository.save(content);
           return "content updated";
        }
       return "content not found to update";
   }
   public List<ContentModel> viewContent(){
       List<Content> contentList=contentRepository.findAll();
       List<ContentModel> contents=new ArrayList<>();
       contentList.forEach(s->
       {
           ContentModel contentModel= entityModel.contentEntity_Model(s);

          // BeanUtils.copyProperties(s,contentModel);
           contents.add(contentModel);
       });
       return contents;
   }

   //subscription
    public SubscriptionPlanModel addedSubscriptionPlan(SubscriptionPlanModel subscriptionPlanModel){
       List<Content> contentList=contentRepository.findAll();
       SubscriptionPlan subscriptionPlan=subscriptionPlanRepository.findById(subscriptionPlanModel.getPlanId()).orElse(null);
       if(subscriptionPlan==null) {
           String planName = subscriptionPlanModel.getPlanName();
           List<Content> contentList1 = new ArrayList<>();
           contentList.forEach(e -> {
               if (e.getSubscriptionLevel().equals(planName)) {
                   contentList1.add(e);
                   SubscriptionPlan subscriptionPlan1 =modelEntity.subscriptionPlanModel_entity(subscriptionPlanModel);

                   //BeanUtils.copyProperties(subscriptionPlanModel, subscriptionPlan1);
                   List<SubscriptionPlan> subscriptionPlanList = e.getSubscriptionPlanList();
                   subscriptionPlanList.add(subscriptionPlan1);
                   e.setSubscriptionPlanList(subscriptionPlanList);
                   contentRepository.save(e);
               }
           });
           subscriptionPlanModel.setContentList(contentList1);
           SubscriptionPlan subscriptionPlan1 = modelEntity.subscriptionPlanModel_entity(subscriptionPlanModel);

           //BeanUtils.copyProperties(subscriptionPlanModel, subscriptionPlan1);
           subscriptionPlanRepository.save(subscriptionPlan1);
           return subscriptionPlanModel;
       }
       return null;
    }
    public List<SubscriptionPlanModel> viewSubscriptionPlans(){
       List<SubscriptionPlan> subscriptionPlanList=subscriptionPlanRepository.findAll();
       List<SubscriptionPlanModel> subscriptionPlanModelList=new ArrayList<>();
       subscriptionPlanList.forEach(s->
       {
           SubscriptionPlanModel subscriptionPlanModel=entityModel.subscriptionPlanModel(s);;
           System.out.println("sssssss"+subscriptionPlanModel);

           //BeanUtils.copyProperties(s,subscriptionPlanModel);
           subscriptionPlanModelList.add(subscriptionPlanModel);
       });
       return subscriptionPlanModelList;
    }
    public String deleteSubscription(int planId) {
        System.out.println("Entered deleted Content");
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.getReferenceById(planId);
        if (subscriptionPlan != null) {
            List<Content> contentList = subscriptionPlan.getContentList();
            contentList.forEach(content -> {
            List<SubscriptionPlan> subscriptionPlanList = content.getSubscriptionPlanList();
            if (subscriptionPlanList.contains(subscriptionPlan)) {
            subscriptionPlanList.remove(subscriptionPlan);
            content.setSubscriptionPlanList(subscriptionPlanList);
            contentRepository.save(content);
        }
        });
            subscriptionPlan.setContentList(new ArrayList<>());
            subscriptionPlanRepository.delete(subscriptionPlan);
            return "Deleted Successfully";
        }
        return "msg";
    }

    public SubscriptionPlan updatedSubscriptionPlans(SubscriptionPlan subscriptionPlan,int planId){
       SubscriptionPlan subscriptionPlan1=subscriptionPlanRepository.findById(planId).orElse(null);
       if(subscriptionPlan!=null){
           subscriptionPlan1.setPlanPrice(subscriptionPlan.getPlanPrice());
           subscriptionPlan1.setPlanName(subscriptionPlan.getPlanName());
           subscriptionPlan1.setPlanDuration(subscriptionPlan.getPlanDuration());

           System.out.println(subscriptionPlan.getPlanFeatures());
           subscriptionPlan1.setPlanFeatures(subscriptionPlan.getPlanFeatures());
           subscriptionPlan1.setUsers(subscriptionPlan.getUsers());
           subscriptionPlan1.setContentList(subscriptionPlan.getContentList());
           subscriptionPlanRepository.save(subscriptionPlan1);
           return subscriptionPlan;
       }
       return null;
    }
    public SubscriptionPlan searchByPlanId(int planId){
       SubscriptionPlan subscriptionPlan=subscriptionPlanRepository.findById(planId).orElse(null);
       if(subscriptionPlan!=null){
           return subscriptionPlan;
       }
       return null;
    }
    public ContentModel searchByContentId(int contentId){
       Content content=contentRepository.findById(contentId).orElse(null);
       if(content!=null){
           ContentModel contentModel=entityModel.contentEntity_Model(content);
           return contentModel;

       }
       return null;
    }
}
