<%@ page import="com.SpringBoot.SubscriptionManagementSystemProject.Model.UserModel" %>
<!DOCTYPE html>
<html>
<head>
    <title>Subscriber Features</title>
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
                background-color:#ff8800;
                color: #fff;
                border: none;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color:#ff8800;
            }
        </style>
</head>
<body>
<center>
<%
UserModel user = (UserModel) request.getAttribute("user");

%>
    <h2>Subscriber Features</h2>
     <input type="text" name="subscriptionStatus" value="${subscriptionStatus}" readonly>




  <form action="subscriptionContentForUser">
     <input type="hidden" name="userId" value="<%= user.getUserId() %>" >
<input type="hidden" name="count" value="${count}">
     <input type="hidden" name="subscriptionStatus" value="${subscriptionStatus}">
         <input type="submit" value="subscriptionContentForUser" required>
     </form>





    </center>
</body>
</html>
