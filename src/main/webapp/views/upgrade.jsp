<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.SpringBoot.SubscriptionManagementSystemProject.Entity.SubscriptionPlan" %>
<%@ page import="com.SpringBoot.SubscriptionManagementSystemProject.Model.UserModel" %>
<html>
<head>
    <title>View Subscription Plans</title>
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
<center><h3>Upgradation</h3></center>
<center><h3>Plans</h3></center>


<table>
    <tr>
        <th>Plan Id</th>
        <th>Plan Name</th>
        <th>Plan Price</th>
        <th>Plan Duration</th>
        <th>Plan Features</th>
    </tr>
    <%
        List<SubscriptionPlan> planList = (List<SubscriptionPlan>) request.getAttribute("subscriptionPlanList");
        int userId = (int) request.getAttribute("userId");

        if (planList != null) {
            for (SubscriptionPlan plan : planList) {
    %>
    <tr>
        <td><%= plan.getPlanId() %></td>
        <td><%= plan.getPlanName() %></td>
        <td><%= plan.getPlanPrice() %></td>
        <td><%= plan.getPlanDuration() %></td>
        <td><%= plan.getPlanFeatures() %></td>
        <td>
            <form action="selectingSubscriptionPlanByUser">
                <input type="hidden" name="planId" value="<%= plan.getPlanId() %>">
                <input type="hidden" name="userId" value="<%= userId %>">
                <input type="hidden" name="count" value="${count}">
                <input type="hidden" name="planPrice" value="<%= plan.getPlanPrice() %>">
                <input type="hidden" name="subscriptionStatus" value="${subscriptionStatus}">
                <input type="submit" value="SelectingSubscriptionPlanByUser">
            </form>
        </td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="6">No Content found</td>
    </tr>
    <% } %>

    <tr>
        <td colspan="6">
            <form action="extra">
                <input type="hidden" name="subscriptionStatus" value="${subscriptionStatus}">
                <input type="hidden" name="count" value="${count}">
                <input type="hidden" name="userId" value="${userId}">
                <input type="hidden" name="planId" value="${planId}">
                <input type="submit" value="SubscriptionPlans">
            </form>
        </td>
    </tr>
</table>

<center><h3><a href="home">Home</a></h3></center>
</body>
</html>
