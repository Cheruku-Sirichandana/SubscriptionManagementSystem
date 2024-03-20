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
<center><h3>Contents</h3></center>

<table>
    <tr>

        <b><td>Content Id</td></b>
        <b><td>Content Title</td></b>
        <b><td>Content Type</td></b>
        <b><td>Category</td></b>
        <b><td>DurationInMinutes</td></b>
        <b><td>Category</td></b>
    </tr>
    <%

        List<Content> contentList = (List<Content>) request.getAttribute("contentList");
        if (contentList != null) {
            for (Content content : contentList) {
    %>
    <tr>
        <td><%= content.getContentId() %></td>
        <td><%= content.getTitle() %></td>
        <td><%= content.getType() %></td>
        <td><%= content.getCategory() %></td>
        <td><%= content.getDurationInMinutes() %></td>
        <td><%= content.getSubscriptionLevel() %></td>
    </tr>

    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6">No Content found</td>
    </tr>
    <% } %>
      <td><form action="UserLogin">
      <input type="hidden" name="subscriptionStatus" value="${subscriptionStatus}">
      <input type="hidden" name="count" value="${count}">
      <input type="submit" value="UserLoginPage"></form></td>
</table>
<td><form action="subscribe">
<input type="hidden" name="userId" value="${userId}">
      <input type="hidden" name="subscriptionStatus" value="${subscriptionStatus}">
      <input type="hidden" name="count" value="${count}">
      <input type="submit" value="Subscribe"></form></td>



</body>
</html>
