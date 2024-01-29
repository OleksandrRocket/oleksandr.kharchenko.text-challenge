<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <link rel="stylesheet" href="styles1.css">
</head>

<body>
<form action="/registration" method="post">
    <label for="name">Enter your name:</label>
    <br>
    <input type="text" id="name" name="name">
    <br>
    <label for="dateBirth">Enter your date of birth:</label>
    <br>
    <input type="text" id="dateBirth" name="dateBirth">
    <br>
    <label for="email">Enter your email:</label>
    <br>
    <input type="text" id="email" name="email">
    <br>
    <label for="password">Enter your password:</label>
    <br>
    <input type="text" id="password" name="password">
    <br>
    <input type="submit" value="Enter">
</body>
</html>
