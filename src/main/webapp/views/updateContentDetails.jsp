<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.SpringBoot.SubscriptionManagementSystemProject.Model.ContentModel" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update content</title>
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
        <form:form action="updatedContent" modelAttribute="contentModel">
            <%
            ContentModel contentModel=(ContentModel) request.getAttribute("contentModel");
            if(contentModel!=null) {
            %>
            <label for="contentId">Content ID:</label><br>
            <input type="text" id="contentId" name="contentId" value="<%= contentModel.getContentId()%>" readonly><br>

            <label for="title">Title:</label><br>
            <form:input path="title" value="<%= contentModel.getTitle()%>"/>
            <form:errors path="title" style="color: red;"/><br>

            <label for="description">Description:</label><br>
            <form:input path="description" value="<%= contentModel.getDescription()%>"/><br>
            <form:errors path="description" style="color: red;"/><br>

            <label for="type">Content Type:</label>
            <form:select path="type">
                <form:options items="${type}" contentType="type" />
            </form:select><br><br>
            <form:errors path="type" style="color: red;"/><br>
            </div>
            <div class="form-group">
                <label for="category">Category:</label>
                <form:select id="category" path="category">
                <option value="<%= contentModel.getCategory() %>"><%= contentModel.getCategory() %></option>
                    <option value=""></option>
                    <option value="Melody">Melody</option>
                    <option value="Action">Action</option>
                    <option value="Drama">Drama</option>
                    <option value="Comedy">Comedy</option>
                    <option value="Romance">Romance</option>
                    <option value="Politics">Politics</option>
                    <option value="MelodyMusic">MelodyMusic</option>
                    <option value="Entertainment">Entertainment</option>
                    <option value="Thriller">Thriller</option>
                    <option value="TeamSport">TeamSport</option>
                    <option value="Single-Men-Sport">SingleMenSport</option>
                </form:select>
                <form:errors path="category" style="color: red;"/><br>
            </div>

            <label for="durationInMinutes">Duration (in minutes):</label><br>
            <form:input path="durationInMinutes" value="<%= contentModel.getDurationInMinutes()%>"/><br>
            <form:errors path="durationInMinutes" style="color: red;"/><br>

            <div class="form-group">
                <label for="subscriptionLevel">Subscription Level:</label><br>
                <form:select id="subscriptionLevel" path="subscriptionLevel">
                <option value="<%= contentModel.getSubscriptionLevel() %>"><%= contentModel.getSubscriptionLevel() %></option>
                    <option value="">Select a subscription level</option>
                    <option value="Basic">Basic</option>
                    <option value="Premium">Premium</option>
                    <option value="Gold">Gold</option>
                    <option value="Platinum">Platinum</option>
                </form:select><br><br>
                <form:errors path="subscriptionLevel" style="color: red;"/><br>
            </div>
            <input type="hidden" name="contentId" value="${contentId}">
            <input type="submit" value="Submit">
            <% } else { %>
               <tr>
                   <td colspan="6">No Content found</td>
               </tr>
            <% } %>
        </form:form>
    </center>
</body>
</html>
