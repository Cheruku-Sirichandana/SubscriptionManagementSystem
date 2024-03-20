<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.SpringBoot.SubscriptionManagementSystemProject.Model.ContentModel" %>
<%@ page import="com.SpringBoot.SubscriptionManagementSystemProject.Model.UserModel" %>

<html>
<head>
    <title>View Users</title>
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
<center><h3>Users</h3></center>

<table>
    <tr>

        <b><td>User Id</td></b>
        <b><td>User name</td></b>
        <b><td>User Email</td></b>
        <b><td>User Password</td></b>
        <b><td>SubscriptionStatus</td></b>

    </tr>
    <%
        List<UserModel> userList = (List<UserModel>) request.getAttribute("userModelList");
        if (userList != null) {
            for (UserModel user : userList) {
    %>
    <tr>
        <td><%= user.getUserId() %></td>
        <td><%= user.getUserName() %></td>
        <td><%= user.getUserEmail() %></td>
        <td><%= user.getUserPassword() %></td>

        <td><%= user.getSubscriptionStatus() %></td>





    </tr>

    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6">No User found</td>
    </tr>
    <% } %>
</table>

<center> <h3><a href="User">Back</a></h3></center>


                    <center><h3><a href="home">Home</a></h3></center>
</body>
</html>
