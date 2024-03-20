<%@page language="java" %>
<html>
<head>
<center>
<title>
WelcomePage
</title>
</center>
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
    </style>

</head>
<body>
<center>
<h2>Welcome</h2>
</center>
<form action="Admin">
<input type="submit" value="Admin" class="nav-link">
</form>
<form action="User">
<input type="submit" value="User" class="nav-link">
</form>
</body>
</htmL>