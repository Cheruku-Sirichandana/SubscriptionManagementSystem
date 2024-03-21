package com.SpringBoot.SubscriptionManagementSystemProject.Controller;

import ch.qos.logback.core.model.Model;
import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Admin;
import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Content;
import com.SpringBoot.SubscriptionManagementSystemProject.Entity.SubscriptionPlan;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.AdminModel;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.ContentModel;
import com.SpringBoot.SubscriptionManagementSystemProject.Model.SubscriptionPlanModel;
import com.SpringBoot.SubscriptionManagementSystemProject.Service.AdminServices;
import com.SpringBoot.SubscriptionManagementSystemProject.ServiceInterface.AdminInterface;
import com.SpringBoot.SubscriptionManagementSystemProject.Validations.AdminValidations;
import com.SpringBoot.SubscriptionManagementSystemProject.Validations.ContentValidations;
import com.SpringBoot.SubscriptionManagementSystemProject.Validations.SubscriptionPlanValidations;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminInterface adminServices;
    @Autowired
    AdminValidations adminValidations;
    @Autowired
    ContentValidations contentValidations;
    @Autowired
    SubscriptionPlanValidations subscriptionPlanValidations;
    @RequestMapping("/home")
    public String home(){
        return "home";
    }
    @RequestMapping("/Admin")
    public String admin(){
        return "Admin";
    }
    @RequestMapping("/AdminRegister")
    public String adminRegister(ModelMap map){
        map.addAttribute("adminModel", new AdminModel());
        return "AdminRegister";
    }
    @RequestMapping("/AdminRegistered")
    public String adminRegistered(@Valid @ModelAttribute("adminModel") AdminModel adminModel, BindingResult bindingResult, ModelMap map){
        adminValidations.validate(adminModel,bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "AdminRegister";
        }
        Admin admin1= adminServices.adminRegistered(adminModel);
        if(admin1!=null){
            map.addAttribute("msg","You have Successfully  Registered");
            return "Admin";
        }
        else{
            map.addAttribute("msg","Admin already present,cant register again..Please add new Credentials for Registration");
            return "AdminRegister";
        }
    }
    @RequestMapping("/AdminLogin")
    public String adminLogin(ModelMap map)
    {
        map.addAttribute("adminModel",new AdminModel());
        return "AdminLogin";
    }
    @RequestMapping("/AdminCheck")
    public String adminCheck(@Valid @ModelAttribute("adminModel") AdminModel adminModel,ModelMap model,BindingResult bindingResult){
        adminValidations.validate(adminModel,bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "AdminLogin";
        }
        Boolean adminFound=adminServices.adminCheck(adminModel);
        if(adminFound){
            return "AdminFeatures";
        }
        else{
            model.addAttribute("msg1","Sorry!!Admin not registered!Please Re-enter the credentials");
            return "AdminLogin";
        }
    }
    @RequestMapping("/AdminFeatures")
    public String adminFeatures(){
        return "AdminFeatures";
    }
    @RequestMapping("/content")
    public String content(){
        return "content";
    }
    @RequestMapping("/addContent")
    public String addContent(ModelMap map){
        map.addAttribute("contentModel",new ContentModel());
        return "addContent";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
    }
    @RequestMapping("/addedContent")
    public String addedContent(@Valid @ModelAttribute("contentModel") ContentModel contentModel,ModelMap model,BindingResult bindingResult)
        {
        contentValidations.validate(contentModel,bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "addContent";
        }
        Content content2= adminServices.addedContent(contentModel);
        if(content2!=null){
            return "redirect:/viewContent";
        }
        else {
            model.addAttribute("msg2", "content is already available..Cannot add again");
            return "addContent";
        }
    }

    @RequestMapping("/deleteContent")
    public String deletingContent(@RequestParam("contentId") int contentId){
         adminServices.deleteContent(contentId);
         return "redirect:/viewContent";
    }
    @RequestMapping("/deleteSubscriptionPlan")
    public String deleteSubscriptionPlan(@RequestParam("planId") int planId,ModelMap model){
        String res=adminServices.deleteSubscriptionPlan(planId);
        if(res.equals("msg")){
            model.addAttribute("msg","Sorry...cannot delete subscription plan because subscriptionplan is already subscribed by some user");
            List<SubscriptionPlanModel> subscriptionPlanModelList=adminServices.viewSubscriptionPlans();
            model.addAttribute("subscriptionModelList",subscriptionPlanModelList);
            return "viewSubscriptionPlanPage";
        }
        return "redirect:/viewSubscriptionPlans";
    }
    @RequestMapping("/updateContent")
    public String updateContent(ModelMap model,int contentId){
        ContentModel contentModel=adminServices.searchByContentId(contentId);
        model.addAttribute("contentId",contentId);
        model.addAttribute("contentModel",contentModel);
        return "updateContentDetails";
    }
    @RequestMapping("/updatedContent")
      public String updatedContent(@ModelAttribute("contentModel") ContentModel contentModel,@RequestParam("contentId") int contentId,BindingResult bindingResult,ModelMap model){
        contentValidations.validate(contentModel, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("contentId",contentId);
            return "updateContentDetails";
        }
        adminServices.updatedContent(contentModel,contentId);
        return "redirect:/viewContent";
    }
    @RequestMapping("/viewContent")
    public String viewContent(ModelMap model){
       List<ContentModel> contentListt= adminServices.viewContent();
       model.addAttribute("contentList1",contentListt);
       return "viewContent";
    }
    //Subscription Plans
    @RequestMapping("/subscriptionPlans")
    public String subscriptionPlan(){
        return "subscriptionPlans";
    }
    @RequestMapping("/addSubscriptionPlan")
    public String addSubscriptionPlan(ModelMap model){
        model.addAttribute("subscriptionPlan",new SubscriptionPlanModel());
        return "addSubscriptionPlan";
    }
    @RequestMapping("/addedSubscriptionPlan")
    public String addedSubscriptionPlan(@Valid @ModelAttribute("subscriptionPlan") SubscriptionPlanModel subscriptionPlanModel,ModelMap model,BindingResult bindingResult){
        subscriptionPlanValidations.validate(subscriptionPlanModel, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "addSubscriptionPlan";
        }

        SubscriptionPlanModel subscriptionPlan=adminServices.addedSubscriptionPlan(subscriptionPlanModel);
        if(subscriptionPlan!=null){
            return "redirect:/viewSubscriptionPlans";
        }
        else {
            model.addAttribute("msg6", "subscriptionPlan is already available..Add new SubscriptioPlan");
            return "addSubscriptionPlan";
        }
    }
    @RequestMapping("/viewSubscriptionPlans")
    public String viewSubscriptionPlans(ModelMap model){
        List<SubscriptionPlanModel> subscriptionPlanModelList=adminServices.viewSubscriptionPlans();
        model.addAttribute("subscriptionModelList",subscriptionPlanModelList);
        return "viewSubscriptionPlans";
    }
    @RequestMapping("/updateSubscriptionPlan")
    public String updateSubscriptionPlan(@RequestParam("planId") int planId,ModelMap model){
        SubscriptionPlan subscriptionPlan=adminServices.searchByPlanId(planId);
    model.addAttribute("subscriptionPlan",subscriptionPlan);
        model.addAttribute("planId",planId);
        return "updatingSubscriptionPlan";
    }
    @RequestMapping("/updatedSubscriptionPlans")
    public String updatedSubscriptionPlans(@Valid @ModelAttribute("subscriptionPlan") SubscriptionPlan subscriptionPlan,@RequestParam("planId") int planId,BindingResult bindingResult,ModelMap model){
        SubscriptionPlan subscriptionPlanModel1=adminServices.updatedSubscriptionPlans(subscriptionPlan,planId);
        if(subscriptionPlanModel1!=null){
            return "redirect:/viewSubscriptionPlans";
        }
        else{
            return "updateSubscriptionPlan";
        }
    }

}

