<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.SpringBoot.SubscriptionManagementSystemProject.Entity.SubscriptionPlan" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Subscription Plans</title>
  <link rel="stylesheet" href="../style.css" type="text/css">
      <style>
          body {
              font-family: Arial, sans-serif;
              background-color: #F4F4F4;
              margin: 0;
              padding: 0;
          }
          .container {
              width: 80%;
              margin: 20px auto;
              background-color: #fff;
              box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
              padding: 20px;
          }
          h2, h3 {
              text-align: center;
          }
          .form-section {
              margin-bottom: 20px;
          }
          .form-section label {
              display: block;
              font-weight: bold;
              margin-bottom: 5px;
          }
          .form-section input[type="text"],
          .form-section input[type="number"],
          .form-section select {
              width: 30%;
              height: 40px;
              box-sizing: border-box;
              padding: 5px;
              margin-bottom: 10px;
          }
          .checkbox-group input[type="checkbox"] {
              margin-right: 10px;
          }
          .checkbox-group label {
              display: inline-block;
              margin-bottom: 5px;
              font-weight: normal; /* Remove boldness from checkboxes */
          }
          .submit-button {
              width: 30%;
              padding: 10px 0;
              background-color: #FF8800;
              color: #fff;
              border: none;
              cursor: pointer;
          }
      </style>
</head>
<body>
<center>
    <form:form action="updatedSubscriptionPlans"  modelAttribute="subscriptionPlan">
    <%
     SubscriptionPlan subscriptionPlan=(SubscriptionPlan) request.getAttribute("subscriptionPlan");
                if(subscriptionPlan!=null) {
                %>
        <div class="form-group">
            <label for="planId">Plan ID:</label><br>
            <input type="text" id="planId" name="planId" value="<%= subscriptionPlan.getPlanId()%>" readonly> <br>
        </div>


                   <div class="form-group">
                       <label for="planName" >Subscription Level:</label><br>
                       <form:select id="planName" path="planName" value="<%= subscriptionPlan.getPlanName()%>">
                       <option value="<%= subscriptionPlan.getPlanName() %>"><%= subscriptionPlan.getPlanName() %></option>
                           <option value="Basic">Basic</option>
                           <option value="Premium">Premium</option>
                           <option value="Gold">Gold</option>
                           <option value="Platinum">Platinum</option>
                       </form:select><br><br>
                       <form:errors path="planName" style="color: red;"/><br>
                   </div>



                    <div class="form-section">
                        <label for="planPrice">Plan Price:</label>
                        <form:input  path="planPrice" value="<%= subscriptionPlan.getPlanPrice()%>"/>


                        <form:errors path="planPrice" style="color: red;"/><br>
                    </div>


<!--
                  <div>
                       <label for="planDuration">Plan Duration (in Months):</label><br>
                       <select id="planDuration" name="planDuration" value="<%= subscriptionPlan.getPlanDuration()%>">
                         <option value="<%= subscriptionPlan.getPlanDuration() %>"><%= subscriptionPlan.getPlanDuration() +" Months"%></option>
                           <option value="1">1 Month</option>
                           <option value="3">3 Months</option>
                           <option value="6">6 Months</option>
                           <option value="12">12 Months</option>
                       </select><br>
                        <form:errors path="planPrice" style="color: red;"/><br>
                        </div>
                        -->

<div>
    <label for="planDuration">Plan Duration (in Months):</label><br>
    <select id="planDuration" name="planDuration">
        <option value="1" <% if (subscriptionPlan.getPlanDuration() == 1) { %> selected <% } %>>1 Month</option>
        <option value="3" <% if (subscriptionPlan.getPlanDuration() == 3) { %> selected <% } %>>3 Months</option>
        <option value="6" <% if (subscriptionPlan.getPlanDuration() == 6) { %> selected <% } %>>6 Months</option>
        <option value="12" <% if (subscriptionPlan.getPlanDuration() == 12) { %> selected <% } %>>12 Months</option>
    </select><br>
    <form:errors path="planDuration" style="color: red;"/><br>
</div>

       <div class="form-section">
           <label>Plan Features:</label><br>
               <div class="checkbox-group">
                   <input type="checkbox" id="PlanFeatures" name="planFeatures" value="HD Streaming"
                       <% if (subscriptionPlan.getPlanFeatures().contains("HD Streaming")) { %> checked <% } %>>
                   <label for="hdStreaming">HD Streaming</label><br>

                   <input type="checkbox" id="adFree" name="planFeatures" value="Ad-Free Viewing"
                       <% if (subscriptionPlan.getPlanFeatures().contains("Ad-Free Viewing")) { %> checked <% } %>>
                   <label for="adFree">Ad-Free Viewing</label><br>

                   <input type="checkbox" id="downloadableContent" name="planFeatures" value="Downloadable Content"
                       <% if (subscriptionPlan.getPlanFeatures().contains("Downloadable Content")) { %> checked <% } %>>
                   <label for="downloadableContent">Downloadable Content</label><br>

                   <input type="checkbox" id="Multilingual" name="planFeatures" value="Multilingual"
                       <% if (subscriptionPlan.getPlanFeatures().contains("Multilingual")) { %> checked <% } %>>
                   <label for="Multilingual">Multilingual</label><br>

                   <input type="checkbox" id="DeviceCompatability" name="planFeatures" value="Device Compatibility"
                       <% if (subscriptionPlan.getPlanFeatures().contains("Device Compatibility")) { %> checked <% } %>>
                   <label for="DeviceCompatability">Device Compatibility</label><br>

                   <input type="checkbox" id="EarlyAccess" name="planFeatures" value="Early Access"
                       <% if (subscriptionPlan.getPlanFeatures().contains("Early Access")) { %> checked <% } %>>
                   <label for="EarlyAccess">Early Access</label><br>
               </div>

       </div>

        </div>

            <% } else { %>
                           <tr>
                               <td colspan="6">No Content found</td>
                           </tr>
                        <% } %>
               <input type="submit" class="submit-button" value="Submit">

           </form:form>

</center>
</body>
</html>
