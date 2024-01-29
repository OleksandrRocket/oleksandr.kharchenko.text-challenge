<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Embark on Your Journey</title>
    <link rel="stylesheet" href="welcome_page.css">
</head>

<body>
<jsp:useBean id="user" scope="session" class="ua.javarush.model.User" />
<h1>Welcome, <%= user.getName() %>! Embark on Your Journey and Discover What Awaits You!</h1>

<form action="/challengePage?game=ufo" method="post">
    <button>Begin UFO Quest</button>
</form>
</body>

</html>
