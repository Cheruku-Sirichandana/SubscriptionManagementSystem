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
import java.util.Optional;
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
    public Content addedContent(ContentModel contentModel) {
        Content content = contentRepository.findById(contentModel.getContentId()).orElse(null);

        if (content == null) {
            Content content1 = modelEntity.contentModel_Entity(contentModel);
            List<SubscriptionPlan> subscriptionPlanList=subscriptionPlanRepository.findAll();
            subscriptionPlanList.forEach(subscriptionPlan -> {
                if(subscriptionPlan.getPlanName().equals(content1.getSubscriptionLevel())){
                    List<Content> contentList=subscriptionPlan.getContentList();
                    contentList.add(content1);
                    subscriptionPlan.setContentList(contentList);
                    subscriptionPlanRepository.save(subscriptionPlan);
                }
            });
            content=content1;

        }

        return content;
    }


    public String deleteContent(int contentId){
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
           contents.add(contentModel);
       });
       return contents;
   }

   //subscription
   public SubscriptionPlanModel addedSubscriptionPlan(SubscriptionPlanModel subscriptionPlanModel) {
       SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(subscriptionPlanModel.getPlanId()).orElse(null);
       if (subscriptionPlan == null) {
           SubscriptionPlan subscriptionPlan1 = modelEntity.subscriptionPlanModel_entity(subscriptionPlanModel);
          // subscriptionPlanRepository.save(subscriptionPlan1);
           List<Content> contentList = contentRepository.findAll();
           contentList.forEach(content -> {
               if(content.getSubscriptionLevel().equals(subscriptionPlan1.getPlanName())){
//                   List<SubscriptionPlan> subscriptionPlanList=content.getSubscriptionPlanList();
//                   subscriptionPlanList.add(subscriptionPlan1);
//                   content.setSubscriptionPlanList(subscriptionPlanList);
                  // contentRepository.save(content);
                   List<Content> contentList1=subscriptionPlan1.getContentList();
                   contentList1.add(content);
                   subscriptionPlan1.setContentList(contentList1);
                   subscriptionPlanRepository.save(subscriptionPlan1);
               }
           });

//           Optional<SubscriptionPlan> newSubscriptionPlan1 = contentRepository.findAll().stream()
//                   .filter(content -> content.getSubscriptionLevel().equals(subscriptionPlan1.getPlanName()))
//                   .map(content -> {
//                       List<SubscriptionPlan> subscriptionPlanList = content.getSubscriptionPlanList();
//                       subscriptionPlanList.add(subscriptionPlan1);
//                       content.setSubscriptionPlanList(subscriptionPlanList);
//                       contentList.add(content);
//                       subscriptionPlan.setContentList(contentList);
//                       contentRepository.save(content);
//                       return subscriptionPlan1;
//                   })
//                   .findFirst();
            }
                 return subscriptionPlanModel;
        }


        // public Content addedContent(ContentModel contentModel) {
        //        Content content = contentRepository.findById(contentModel.getContentId()).orElse(null);
        //
        //        if (content == null) {
        //            Content content1 = modelEntity.contentModel_Entity(contentModel);
        //            contentRepository.save(content1);
        //        List<SubscriptionPlan> subscriptionPlanList=new ArrayList<>();
        //            Optional<Content> newContent = subscriptionPlanRepository.findAll().stream()
        //                    .filter(subscriptionPlan -> subscriptionPlan.getPlanName().equals(content1.getSubscriptionLevel()))
        //                    .map(subscriptionPlan -> {
        //                        List<Content> contentList = subscriptionPlan.getContentList();
        //                        contentList.add(content1);
        //                        subscriptionPlan.setContentList(contentList);
        //                        subscriptionPlanList.add(subscriptionPlan);
        //                        content1.setSubscriptionPlanList(subscriptionPlanList);
        //                        subscriptionPlanRepository.save(subscriptionPlan);
        //                        return content1;
        //                    })
        //                    .findFirst();
        //
        //            return newContent.orElse(null);
        //        }
        //
        //        return content;
        //    }
    public List<SubscriptionPlanModel> viewSubscriptionPlans(){
       List<SubscriptionPlan> subscriptionPlanList=subscriptionPlanRepository.findAll();
       List<SubscriptionPlanModel> subscriptionPlanModelList=new ArrayList<>();
       subscriptionPlanList.forEach(s->
       {
           SubscriptionPlanModel subscriptionPlanModel=entityModel.subscriptionPlanModel(s);;
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
       if(subscriptionPlan1!=null){
           subscriptionPlan.setPlanId(planId);
           subscriptionPlanRepository.save(subscriptionPlan);
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
