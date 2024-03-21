<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.SpringBoot.SubscriptionManagementSystemProject.Model.SubscriptionPlanModel" %>
<%@ page import="com.SpringBoot.SubscriptionManagementSystemProject.Model.UserModel" %>

<!DOCTYPE html>
<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="../style.css" type="text/css">
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

        .custom-input {
            width: 200px;
            height: 40px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: inline-block;
            font-weight: bold;
            width: 120px; /* Adjust width as needed */
            vertical-align: top; /* Align labels to the top */
        }
        .form-group input[type="number"],
        .form-group input[type="text"] {
            vertical-align: top; /* Align input/select boxes to the top */
            width: 200px; /* Adjust width as needed */
        }
    </style>
</head>
<body>
    <center>
    <%
    int planId=(int) request.getAttribute("planId");
      int userId=(int) request.getAttribute("userId");%>
        <h2>Payment Details</h2>
        <form action="paidPaymentForSubscription" modelAttribute="paymentModel">
            <h3>${msg2}</h3>
            <%
            Double planPrice=(Double) request.getAttribute("planPrice");

            %>


            <div class="form-group">
                <label for="amount">Amount:</label>
                <input type="number" id="amount" name="amount" value="<%=planPrice%>" required>


            </div>

            <div class="form-group">
                <label for="formattedDate">Payment Date:</label>
                <input type="date" id="formattedDate" name="formattedDate" value="<%= java.time.LocalDate.now().toString() %>" required>

            </div>



            <div class="form-group">
                <label for="paymentMethod">Payment Method:</label>
                <select id="paymentMethod" name="paymentMethod" required>
                    <option value="credit_card">Credit Card</option>
                    <option value="paypal">PayPal</option>
                    <option value="Gpay">Google Pay</option>
                </select>
            </div>
<div class="form-group">
    <label for="paymentStatus">Payment Status:</label>

        <input type="text" name="paymentStatus" value="${paymentStatus}">


</div>
 <input type="hidden" name="userId" value="<%=userId%>" >
         <input type="hidden" name="planId" value="<%=planId%>" >
          <input type="hidden" name="paymentStatus1" value="${paymentStatus1}">
         <input type="hidden" name="subscriptionStatus" value="${subscriptionStatus}" >
         <input type="hidden" name="count" value="${count}">
            <input type="submit" value="Submit">




        </form>

    </center>
</body>
</html>
