<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login and Registration</title>
    <link rel="stylesheet" href="styles1.css">
</head>
</head>
<body>

<h3>Enter your email and password or register</h3>
<br>

<form action="/login" method="post">
    <label for="email">Enter your email:</label>
    <br>
    <input type="text" id="email" name="email">
    <br>
    <label for="password">Password:</label>
    <br>
    <input type="password" id="password" name="password">
    <br>
    <input type="submit" value="Log in">
    <br>

</form>
<form action="/registration" method="get" class="registration-form">
    <input type="submit" value="Registration">
</form>

</body>
</html>
