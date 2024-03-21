package com.SpringBoot.SubscriptionManagementSystemProject.Service;

import com.SpringBoot.SubscriptionManagementSystemProject.Conversions.Entity_Model;
import com.SpringBoot.SubscriptionManagementSystemProject.Conversions.Model_Entity;
import com.SpringBoot.SubscriptionManagementSystemProject.Entity.*;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.*;
import com.SpringBoot.SubscriptionManagementSystemProject.Repository.ContentRepository;
import com.SpringBoot.SubscriptionManagementSystemProject.Repository.PaymentRepository;
import com.SpringBoot.SubscriptionManagementSystemProject.Repository.SubscriptionPlanRepository;
import com.SpringBoot.SubscriptionManagementSystemProject.Repository.UserRepository;
import com.SpringBoot.SubscriptionManagementSystemProject.ServiceInterface.UserInterface;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServices implements UserInterface {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private Entity_Model entityModel;
    @Autowired
    private Model_Entity modelEntity;
    public UserModel userRegistered(UserModel userModel){
       Users user=userRepository.findByUserName(userModel.getUserName());
       if(user==null){
           Users user1= modelEntity.userModel_Entity(userModel);

           //BeanUtils.copyProperties(userModel,user1);
           user1.setUserId(userModel.getUserId());
           user1.setRole("ROLE_USER");
           user1.setUserName(userModel.getUserName());
           user1.setUserPassword(passwordEncoder.encode(userModel.getUserPassword()));
           user1.setUserEmail(userModel.getUserEmail());
           user1.setSubscriptionStatus(userModel.getSubscriptionStatus());
           userRepository.save(user1);
           return userModel;
       }
       return null;
   }
    public Boolean userCheck(UserModel userModel) {
        return userRepository.findAll().stream().anyMatch(users -> users.getUserId()==userModel.getUserId() && passwordEncoder.matches(userModel.getUserPassword(),users.getUserPassword()));
    }
    public List<UserModel> viewUsers(){
        List<Users> usersList=userRepository.findAll();
        List<UserModel> userModelList=new ArrayList<>();
        usersList.forEach(s->
        {
            UserModel userModel = entityModel.userEntity_Model(s);
            userModelList.add(userModel);
        });
        return userModelList;
    }
    public SubscriptionStatus subscriptionStatus(UserModel userModel){
       Users users=userRepository.findById(userModel.getUserId()).orElse(null);
       if(users!=null){
           SubscriptionStatus subscriptionStatus=users.getSubscriptionStatus();

           return subscriptionStatus;
       }
       return null;
    }
    public SubscriptionStatus updatedStatus(UserModel userModel){
        System.out.println(userModel.getUserId());
        Users user=userRepository.findById(userModel.getUserId()).orElse(null);
        if (user != null && passwordEncoder.matches(userModel.getUserPassword(),user.getUserPassword()))
        {
                SubscriptionStatus subscriptionStatus=user.getSubscriptionStatus();

                return subscriptionStatus;
        }
        return SubscriptionStatus.NONE;
    }


    public List<Content> display(int userId, int planId, SubscriptionStatus subscriptionStatus) {
        Users user=userRepository.findById(userId).orElse(null);
        SubscriptionPlan newSubscriptionPlan = subscriptionPlanRepository.findById(planId).orElse(null);
        if (user != null && newSubscriptionPlan != null) {
            SubscriptionPlan currentSubscriptionPlan = user.getSubscriptionPlan();
            if (currentSubscriptionPlan != null) {
                List<Users> usersOfCurrentPlan = currentSubscriptionPlan.getUsers();
                usersOfCurrentPlan.remove(user);
                subscriptionPlanRepository.save(currentSubscriptionPlan);
            }
            user.setSubscriptionPlan(newSubscriptionPlan);
            userRepository.save(user);
            user.setSubscriptionStatus(subscriptionStatus);
            return newSubscriptionPlan.getContentList();
        }
        return null;
    }
    public SubscriptionStatus setSubscriptionStatus(int userId,int planId){
        Users users=userRepository.findById(userId).orElse(null);
        if(users!=null && users.getSubscriptionStatus()!=SubscriptionStatus.ACTIVE){
            users.setSubscriptionStatus(SubscriptionStatus.ACTIVE);
            SubscriptionPlan subscriptionPlan=subscriptionPlanRepository.findById(planId).orElse(null);
             users.setSubscriptionPlan(subscriptionPlan);
             try{
                List<Users> planUsers = new ArrayList<>(subscriptionPlan.getUsers());
                planUsers.add(users);
                subscriptionPlan.setUsers(planUsers);
             } catch (NullPointerException e){
                List<Users> planUsers = new ArrayList<>();
                planUsers.add(users);
                subscriptionPlan.setUsers(planUsers);
            }
            userRepository.save(users);
            return users.getSubscriptionStatus();
        }
        if(users!=null && users.getSubscriptionStatus()==SubscriptionStatus.ACTIVE){
            return users.getSubscriptionStatus();
        }
        return SubscriptionStatus.NONE;
    }

    public int planId(int userId){
        Users users=userRepository.findById(userId).orElse(null);
        if(users!=null){
            int planId=users.getSubscriptionPlan().getPlanId();
            return planId;
        }
        return 0;
    }
    public String setPaymentStatus(String paymentStatus){
       Payment payment=new Payment();
      paymentStatus="Success";
      return paymentStatus;
    }
    public PaymentModel save(PaymentModel paymentModel){
        Payment payment = new Payment();
        payment.setPaymentId(paymentModel.getPaymentId());
        payment.setPaymentMethod(paymentModel.getPaymentMethod());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime paymentDate = LocalDate.parse(paymentModel.getFormattedDate(), formatter).atStartOfDay();
        paymentModel.setPaymentDate(paymentDate);
        payment.setPaymentDate(paymentModel.getPaymentDate());
        payment.setPaymentStatus(paymentModel.getPaymentStatus());
        payment.setAmount(paymentModel.getAmount());
        System.out.println("PAYMENT"+paymentModel.getPaymentDate());
        System.out.println("Pa"+paymentModel.getPaymentStatus());
        Payment savedPayment = paymentRepository.save(payment);

        if (savedPayment != null) {
        return paymentModel;
        } else {

            return null;
        }
    }


    public List<Content> subscriptionContentForUser(int userId){
        Users user=userRepository.findById(userId).orElse(null);

       if(user!=null) {
           SubscriptionStatus subscriptionStatus = user.getSubscriptionStatus();
           if (subscriptionStatus == SubscriptionStatus.ACTIVE) {
               SubscriptionPlan subscriptionPlan = user.getSubscriptionPlan();
               System.out.println(user.getSubscriptionPlan());
               System.out.println(subscriptionPlan.getContentList());
               return subscriptionPlan.getContentList();
           }
           else {
               List<Content> freeContents = contentRepository.findBySubscriptionLevel("Free");
               return freeContents;
           }
       }
       return null;
    }
    public List<SubscriptionPlan> upgradeSubscription(int userId,String planName){
    Users users=userRepository.findById(userId).orElse(null);
       if(users!=null){
           if(planName.equals("Basic")){
               List<SubscriptionPlan> subcriptionPlans=subscriptionPlanRepository.findAll();
               List<SubscriptionPlan> subscriptionPlanList=new ArrayList<>();
               subcriptionPlans.forEach(s->
               {
                   if(s.getPlanName().equals("Premium") || s.getPlanName().equals("Gold") || s.getPlanName().equals("Platinum")){
                   subscriptionPlanList.add(s);
                   }
               });
               return subscriptionPlanList;

           }
           if(planName.equals("Premium")){
               List<SubscriptionPlan> subcriptionPlans=subscriptionPlanRepository.findAll();
               List<SubscriptionPlan> subscriptionPlanList=new ArrayList<>();
               subcriptionPlans.forEach(s->
               {
                   if( s.getPlanName().equals("Gold") || s.getPlanName().equals("Platinum")){
                       subscriptionPlanList.add(s);
                   }
               });
               return subscriptionPlanList;
           }
           if(planName.equals("Gold")){
               List<SubscriptionPlan> subcriptionPlans=subscriptionPlanRepository.findAll();
               List<SubscriptionPlan> subscriptionPlanList=new ArrayList<>();
               subcriptionPlans.forEach(s->
               {
                   if( s.getPlanName().equals("Platinum")){
                       subscriptionPlanList.add(s);
                   }
               });
               return subscriptionPlanList;

           }
       }
       return null;
}
public String findPlanName(SubscriptionPlanModel subscriptionPlanModel){
       int planId=subscriptionPlanModel.getPlanId();
       SubscriptionPlan subscriptionPlan=subscriptionPlanRepository.findById(planId).orElse(null);
       if(subscriptionPlan!=null){
           return subscriptionPlan.getPlanName();
       }
       return "null";
}
    public String removeSubscriptionPlan(int userId, int planId) {
        Users user=userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "User not found";
        }
        SubscriptionPlan newSubscriptionPlan = subscriptionPlanRepository.findById(planId).orElse(null);
        if (newSubscriptionPlan == null) {
            return "New subscription plan not found";
        }
        SubscriptionPlan currentSubscriptionPlan = user.getSubscriptionPlan();
        if (currentSubscriptionPlan != null) {
            if (currentSubscriptionPlan.getPlanId() == newSubscriptionPlan.getPlanId()) {
                return "User already subscribed to this plan";
            }
            List<Users> userList = currentSubscriptionPlan.getUsers();
            userList.remove(user);
            subscriptionPlanRepository.save(currentSubscriptionPlan);
        }
        user.setSubscriptionPlan(newSubscriptionPlan);
        userRepository.save(user);
        return "Successfully updated user's subscription plan";
    }
    public Integer getPlanId(int userId) {
        Users users=userRepository.findById(userId).orElse(null);
        if (users != null) {
            if (users.getSubscriptionPlan() != null) {
                int planId = users.getSubscriptionPlan().getPlanId();
                return planId;
            }
        }
        return null;
    }
    public List<SubscriptionPlan> degradeSubscription(int userId,String planName){
    Users user=userRepository.findById(userId).orElse(null);
    if(user!=null){
           if(planName.equals("Premium")){
               List<SubscriptionPlan> subcriptionPlans=subscriptionPlanRepository.findAll();
              List<SubscriptionPlan> subscriptionPlanList=new ArrayList<>();
              subcriptionPlans.forEach(s->
               {
                  if( s.getPlanName().equals("Basic")){
                       subscriptionPlanList.add(s);
                }
               });
               return subscriptionPlanList;
           }
          if(planName.equals("Gold")){
             List<SubscriptionPlan> subcriptionPlans=subscriptionPlanRepository.findAll();
               List<SubscriptionPlan> subscriptionPlanList=new ArrayList<>();
               subcriptionPlans.forEach(s->
               {
                   if( s.getPlanName().equals("Basic") || s.getPlanName().equals("Premium")){
                     subscriptionPlanList.add(s);
                   }
              });
              subscriptionPlanList.forEach(i -> System.out.println(i.getPlanName()));
              return subscriptionPlanList;
          }
          if(planName.equals("Platinum")){
              List<SubscriptionPlan> subcriptionPlans=subscriptionPlanRepository.findAll();
              List<SubscriptionPlan> subscriptionPlanList=new ArrayList<>();
              subcriptionPlans.forEach(s->
              {
                  if( s.getPlanName().equals("Basic") || s.getPlanName().equals("Premium") || s.getPlanName().equals("Gold")){
                      subscriptionPlanList.add(s);
                  }
              });
              return subscriptionPlanList;
          }
      }
       return null;
}


}
