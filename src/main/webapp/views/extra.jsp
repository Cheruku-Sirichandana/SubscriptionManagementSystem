<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.SpringBoot.SubscriptionManagementSystemProject.Entity.Content" %>

<html>
<head>
    <title>View Content Of UserSubscriptionPlan</title>
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

<center>
<h3>U have already subscribed!!!!!!!!!</h3></center>


<form action="upgrade">
    <input type="hidden" name="userId" value="${userId}" >
    <input type="hidden" name="subscriptionStatus" value="${subscriptionStatus}">
    <input type="hidden" name="planModel" value="${planModel}">
    <input type="hidden" name="planName" value="${planName}">
    <input type="hidden" name="count" value="${count}">
    <input type="hidden" name="planId" value="${planId}">
        <input type="submit" value="upgrade" required>
    </form>
    <form action="degrade">
        <input type="hidden" name="userId" value="${userId}" >
           <input type="hidden" name="subscriptionStatus" value="${subscriptionStatus}">
           <input type="hidden" name="planModel" value="${planModel}">
           <input type="hidden" name="planName" value="${planName}">
           <input type="hidden" name="count" value="${count}">
           <input type="hidden" name="planId" value="${planId}">
            <input type="submit" value="degrade" required>
        </form>
</html>
