<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.SpringBoot.SubscriptionManagementSystemProject.Model.SubscriptionPlanModel" %>

<html>
<head>
    <title>ViewSubscription Plans</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #F4F4F4;
            margin: 0;
            padding: 0;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #FF8800;
            color: #fff;
        }

        tr:hover {
            background-color: #F5F5F5;
        }

        a {
            text-decoration: none;
            color: #FF8800;
        }

        form {
            text-align: center;
            margin-top: 20px;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #ff8800;
            color: #fff;
            border: none;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #ff8800;
        }
    </style>
</head>
<body>
<center><h3>SubscriptionPlans</h3></center>
<center><h3>${msg}</h3></center>

<table>
    <tr>

        <b><td>Plan Id</td></b>
        <b><td>Plan Name</td></b>
        <b><td>Plan Price</td></b>
        <b><td>Plan Duration</td></b>
        <b><td>Plan Features</td></b>

    </tr>
    <%
        List<SubscriptionPlanModel> planList = (List<SubscriptionPlanModel>) request.getAttribute("subscriptionModelList");
        if (planList != null) {
            for (SubscriptionPlanModel plan : planList) {
    %>
    <tr>
        <td><%= plan.getPlanId() %></td>
        <td><%= plan.getPlanName() %></td>
        <td><%= plan.getPlanPrice() %></td>
        <td><%= plan.getPlanDuration() %></td>
         <td> <%= plan.getPlanFeatures() %></td>



         <td><form action="deleteSubscriptionPlan" method="post">
         <input type="hidden" name="planId" value="<%= plan.getPlanId() %>" >
                    <input type="submit" value="DeleteSubscriptionPlan"></form></td>

                     <td><form action="updateSubscriptionPlan">
                      <input type="hidden" name="planId" value="<%= plan.getPlanId() %>" >
                       <input type="submit" value="UpdateSubscriptionPlan"></form></td>



    </tr>

    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6">No Content found</td>
    </tr>
    <% } %>
</table>

<center> <h3><a href="subscriptionPlans">Back</a></h3></center>

<center> <h3><a href="AdminFeatures">AdminFeatures</a></h3></center>
                    <center><h3><a href="home">Home</a></h3></center>
</body>
</html>
