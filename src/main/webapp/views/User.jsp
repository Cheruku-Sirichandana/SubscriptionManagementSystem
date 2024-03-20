
<!DOCTYPE html>
<html>
<head>
    <title>Admin HomePage</title>
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
    <h2>User HomePage</h2>
   <h3> ${msg} <h3>
    <form action="UserRegister">
<input type="hidden" id="subscriptionStatus" name="subscriptionStatus" value="${subscriptionStatus}">
<input type="hidden" name="count" value="${count}">
        <input type="submit" value="UserRegister" required>
    </form>
    <form action="/UserLogin">
<input type="hidden" id="subscriptionStatus" name="subscriptionStatus" value="${subscriptionStatus}">
<input type="hidden" id="count" name="count" value="${count}">

            <input type="submit" value="UserLogin" required>
        </form>
           <h3> <a href="home">Home</a></h3>

    </center>
</body>
</html>
