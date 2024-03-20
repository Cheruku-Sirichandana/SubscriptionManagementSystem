<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Content</title>
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
            background-color: #ff8800;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #ff8800;
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
            width: 150px;
            vertical-align: top;
        }
        .form-group select, .form-group input {
            vertical-align: top;
        }
    </style>
</head>
<body>
    <center>
        <h2>Add Content</h2>
        <form:form action="addedContent" modelAttribute="contentModel">
            <h3>${msg2}</h3>
    <label for="contentId">Content Id:</label><br>
        <form:input type="number" path="contentId"/><br>
          <form:errors path="contentId" style="color: red;"/><br>
     <div class="form-group">
                <label for="title">Title:</label><br>
                <form:input path="title"/><br>
                <form:errors path="title" style="color: red;"/><br>
    </div>
            <label for="description">Description:</label><br>
                        <form:input path="description"/><br>
                        <form:errors path="description" style="color: red;"/><br>
            <div class="form-group">
                <label for="type">Type:</label>
                <form:select id="type" path="type">
                    <option value="MOVIE">Movie</option>
                    <option value="TV_SHOW">TV Show</option>
                    <option value="WEB_SERIES">Web Series</option>
                    <option value="SPORTS">Sports</option>
                    <option value="NEWS">News</option>
                    <option value="MUSIC">Music</option>
                </form:select>
                <form:errors path="type" style="color: red;"/><br>
            </div>

            <div class="form-group">
                <label for="category">Category:</label>
                <form:select id="category" path="category">
                    <option value="">Select a category</option>
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
            <form:input path="durationInMinutes"/><br>
            <form:errors path="durationInMinutes" style="color: red;"/><br>

            <div class="form-group">
                <label for="subscriptionLevel">Subscription Level:</label><br>
                <form:select id="subscriptionLevel" path="subscriptionLevel">
                    <option value="">Select a subscription level</option>
                    <option value="Free">Free</option>
                    <option value="Basic">Basic</option>
                    <option value="Premium">Premium</option>
                    <option value="Gold">Gold</option>
                    <option value="Platinum">Platinum</option>
                </form:select><br><br>
                 <form:errors path="subscriptionLevel" style="color: red;"/><br>
            </div>

            <input type="submit" value="Submit">
        </form:form>
    </center>
</body>
</html>
