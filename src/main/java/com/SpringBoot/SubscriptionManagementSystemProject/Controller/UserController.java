package com.SpringBoot.SubscriptionManagementSystemProject.Controller;

import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Content;
import com.SpringBoot.SubscriptionManagementSystemProject.Entity.SubscriptionPlan;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.PaymentModel;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.SubscriptionPlanModel;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.SubscriptionStatus;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.UserModel;
import com.SpringBoot.SubscriptionManagementSystemProject.Repository.AdminRepository;
import com.SpringBoot.SubscriptionManagementSystemProject.Service.AdminServices;
import com.SpringBoot.SubscriptionManagementSystemProject.Service.UserServices;
import com.SpringBoot.SubscriptionManagementSystemProject.ServiceInterface.UserInterface;
import com.SpringBoot.SubscriptionManagementSystemProject.Validations.UserValidations;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserInterface userServices;
    @Autowired
    private AdminServices adminServices;
    @Autowired
    private UserValidations userValidations;

    @RequestMapping("/User")
    public String user(ModelMap model){
         int count=1;
        SubscriptionStatus subscriptionStatus = SubscriptionStatus.NONE;
        model.addAttribute("subscriptionStatus",subscriptionStatus);
        model.addAttribute("count",count);
        System.out.println("COUNT"+count);
        return "User";
    }
    @RequestMapping("/UserRegister")
    public String userRegister(ModelMap model,@RequestParam("subscriptionStatus") SubscriptionStatus subscriptionStatus,@RequestParam("count") int count){
        model.addAttribute("subscriptionStatus",subscriptionStatus);
        model.addAttribute("count",count);
        model.addAttribute("userModel",new UserModel());
        return "userRegistration";
    }
    @RequestMapping("/UserRegistered")
    public String userRegistered(@Valid @ModelAttribute("userModel") UserModel userModel, ModelMap model, @RequestParam("subscriptionStatus") SubscriptionStatus subscriptionStatus, @RequestParam("count") int count, BindingResult bindingResult){
        userValidations.validate(userModel,bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("subscriptionStatus",subscriptionStatus);
            model.addAttribute("count",count);
            System.out.println(bindingResult.getAllErrors());
            return "userRegistration";
        }
        UserModel userModel1= userServices.userRegistered(userModel);
       model.addAttribute("count",count);
       model.addAttribute("subscriptionStatus",subscriptionStatus);
       if(userModel1!=null){
           model.addAttribute("msg", "User is registered successfully");
           return "User";
       }
       model.addAttribute("msg1","user is already available..Please add new USER");
       return "userRegistration";
    }

    @RequestMapping("/viewUsers")
    public String viewUsers(ModelMap model){
        List<UserModel> userModelList=userServices.viewUsers();
        model.addAttribute("userModelList",userModelList);
        return "viewUsers";
    }

    @RequestMapping("/UserLogin")
    public String userLogin(ModelMap model,@RequestParam("subscriptionStatus") SubscriptionStatus subscriptionStatus,@RequestParam("count") int count) {
        model.addAttribute("subscriptionStatus", subscriptionStatus);
        model.addAttribute("count",count);
        model.addAttribute("userModel",new UserModel());
        return "userLogin";
    }
    @RequestMapping("/UserCheck")
    public String userCheck(@Valid @ModelAttribute("userModel") UserModel userModel,ModelMap model,@RequestParam("userId") int userId ,@RequestParam("subscriptionStatus") SubscriptionStatus subscriptionStatus,@RequestParam("count") int count,BindingResult bindingResult){
        userValidations.validate1(userModel,bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("subscriptionStatus",subscriptionStatus);
            model.addAttribute("count",count);
            System.out.println(bindingResult.getAllErrors());
            return "userLogin";
        }
        System.out.println(userModel.getUserId()+"   "+userModel.getUserPassword());
        Boolean userFound=userServices.userCheck(userModel);
        System.out.println(userFound);
        if(userFound){
            model.addAttribute("user",userModel);
            SubscriptionStatus subscriptionStatus1=userServices.updatedStatus(userModel);
            model.addAttribute("subscriptionStatus",subscriptionStatus1);
            model.addAttribute("count",count);
            if(subscriptionStatus1.equals(SubscriptionStatus.ACTIVE)){
               int planId= userServices.getPlanId(userId);
               return "subscriberFeatures";
            }
            return "UserFeatures";
        }
        else{
            model.addAttribute("msg1","Sorry!!User not registered!Please Re-enter the credentials");
            return "redirect:/UserLogin?subscriptionStatus=" + subscriptionStatus + "&count=" + count;
        }
    }
    @RequestMapping("/subscribe")
    public String viewSubscriptionPlansForUser(ModelMap model, @RequestParam("userId") int userId, @RequestParam("subscriptionStatus") SubscriptionStatus subscriptionStatus,@RequestParam("count") int count){
        List<SubscriptionPlanModel> subscriptionPlanModelList=adminServices.viewSubscriptionPlans();
        model.addAttribute("userId",userId);
        model.addAttribute("subscriptionStatus",subscriptionStatus);
        model.addAttribute("count",count);
        model.addAttribute("subscriptionModelList",subscriptionPlanModelList);
        return "UserSelectingFromSubscriptionPlans";
    }
    @RequestMapping("/selectingSubscriptionPlanByUser")
    public String selectingSubscriptionPlanByUser(@RequestParam("userId") int userId, @RequestParam("planId") int planId,@RequestParam("planPrice") double planPrice,@RequestParam("subscriptionStatus") SubscriptionStatus subscriptionStatus,@RequestParam("count") int count, ModelMap model){
        model.addAttribute("userId",userId);
        model.addAttribute("planId",planId);
        model.addAttribute("planPrice",planPrice);
        model.addAttribute("subscriptionStatus",subscriptionStatus);
        String paymentStatus="Pending";
        model.addAttribute("paymentStatus",paymentStatus);
        String paymentStatus1=userServices.setPaymentStatus(paymentStatus);
        model.addAttribute("paymentStatus1",paymentStatus1);
        model.addAttribute("paymentModel",new PaymentModel());
        model.addAttribute("count",count);
        return "payment";
    }
    @RequestMapping("/paidPaymentForSubscription")
    public String paidPaymentForSubscription(@ModelAttribute("paymentModel") PaymentModel paymentModel,@RequestParam("subscriptionStatus") SubscriptionStatus subscriptionStatus,@RequestParam("userId") int userId, @RequestParam("planId") int planId,SubscriptionPlanModel subscriptionPlanModel, @RequestParam("count") int count, ModelMap model){
        model.addAttribute("userId",userId);
        model.addAttribute("planId",planId);
        model.addAttribute("planModel",subscriptionPlanModel);
        model.addAttribute("count",count);
        String planName=userServices.findPlanName(subscriptionPlanModel);
        model.addAttribute("planName",planName);
        model.addAttribute("subscriptionStatus",subscriptionStatus);
        model.addAttribute("paymentModel",paymentModel);
        if(subscriptionStatus==SubscriptionStatus.NONE && count==1){
            SubscriptionStatus subscriptionStatus1 = userServices.setSubscriptionStatus(userId,planId);
            model.addAttribute("subscriptionStatus", subscriptionStatus1);
            userServices.save(paymentModel);
            List<Content> contentList = userServices.display(userId, planId, subscriptionStatus);
            if (contentList != null) {
                model.addAttribute("contentList1", contentList);
                SubscriptionStatus subscriptionStatus2 = SubscriptionStatus.NONE;
                model.addAttribute("subscriptionStatus2", subscriptionStatus2);
                return "viewSubscriptionContentOfUser";
            }

        }
        if( subscriptionStatus==SubscriptionStatus.ACTIVE && count==1) {
            SubscriptionStatus subscriptionStatus1 = userServices.setSubscriptionStatus(userId,planId);
            userServices.removeSubscriptionPlan(userId, planId);
            model.addAttribute("subscriptionStatus", subscriptionStatus1);
            userServices.save(paymentModel);
            List<Content> contentList = userServices.display(userId, planId, subscriptionStatus);
            System.out.println(contentList);
            if (contentList != null) {
                model.addAttribute("contentList1", contentList);
                SubscriptionStatus subscriptionStatus2 = SubscriptionStatus.NONE;
                model.addAttribute("subscriptionStatus2", subscriptionStatus2);
                return "viewSubscriptionContentOfUser";
            }
        }
        if( subscriptionStatus==SubscriptionStatus.ACTIVE && count!=1) {
            SubscriptionStatus subscriptionStatus1 = userServices.setSubscriptionStatus(userId,planId);
            userServices.removeSubscriptionPlan(userId, planId);
            model.addAttribute("subscriptionStatus", subscriptionStatus1);
            userServices.save(paymentModel);
            List<Content> contentList = userServices.display(userId, planId, subscriptionStatus);

            if (contentList != null) {
                model.addAttribute("contentList1", contentList);
                SubscriptionStatus subscriptionStatus2 = SubscriptionStatus.NONE;
                model.addAttribute("subscriptionStatus2", subscriptionStatus2);
                return "viewSubscriptionContentOfUser";
            }
        }
        else{
            return "payment";
        }
        System.out.println("PLAN ID IS"+planId);
        return "extra";
    }
    @RequestMapping("/subscriptionContentForUser")
    public String subscriptionContentForUser(@RequestParam("count") int count,@RequestParam("userId") int userId,@RequestParam("subscriptionStatus") SubscriptionStatus subscriptionStatus, ModelMap model){
        List<Content> contentList=userServices.subscriptionContentForUser(userId);
        if(contentList!=null){
            model.addAttribute("contentList",contentList);
            model.addAttribute("userId",userId);
            model.addAttribute("subscriptionStatus",subscriptionStatus);
            model.addAttribute("count",count);
            Integer planId=userServices.getPlanId(userId);
           if(planId==null){
               return "viewFreeContent";
           }
            model.addAttribute("planId",planId);
            return "viewSubscriptionContentForUser";
        }
        return "content";
    }
    @RequestMapping("/extra")
    public String extra(@ModelAttribute("paymentModel") PaymentModel paymentModel,@RequestParam("subscriptionStatus") SubscriptionStatus subscriptionStatus,@RequestParam("userId") int userId, @RequestParam("planId") int planId,SubscriptionPlanModel subscriptionPlanModel, @RequestParam("count") int count, ModelMap model){
        String planName=userServices.findPlanName(subscriptionPlanModel);
        model.addAttribute("planName",planName);
        model.addAttribute("count",count);
        model.addAttribute("subscriptionStatus",subscriptionStatus);
        model.addAttribute("userId",userId);
        model.addAttribute("planId",planId);
        model.addAttribute("planModel",subscriptionPlanModel);
        model.addAttribute("planName",planName);
        return "extra";
    }

    @RequestMapping("/upgrade")
    public String upgradeSubscription(@RequestParam("userId") int userId, @RequestParam("planName") String planName,@RequestParam("subscriptionStatus") SubscriptionStatus subscriptionStatus,@RequestParam("count") int count,@RequestParam("planId") int planId, ModelMap model){
        count=count+1;
        List<SubscriptionPlan> subscriptionPlanList=userServices.upgradeSubscription(userId,planName);
        model.addAttribute("subscriptionPlanList",subscriptionPlanList);
        model.addAttribute("userId",userId);
        model.addAttribute("count",count);
        model.addAttribute("planId",planId);
        model.addAttribute("subscriptionStatus",subscriptionStatus);
        return "upgrade";
    }

    @RequestMapping("/degrade")
    public String degradeSubscriptionPlan(@RequestParam("userId") int userId,@RequestParam("planName") String planName,@RequestParam("subscriptionStatus") SubscriptionStatus subscriptionStatus,@RequestParam("count") int count,@RequestParam("planId") int planId, ModelMap model){
        count=count+1;
        List<SubscriptionPlan> subscriptionPlanList=userServices.degradeSubscription(userId,planName);
        model.addAttribute("subscriptionPlanList",subscriptionPlanList);
        model.addAttribute("userId",userId);
        model.addAttribute("planId",planId);
        model.addAttribute("count",count);
        model.addAttribute("subscriptionStatus",subscriptionStatus);
        return "downgrade";
    }
    @RequestMapping("/someOtherFunctionalities")
    public String someOtherFunctionalities(){
        return "someOtherFunctionalities";
    }

  }

