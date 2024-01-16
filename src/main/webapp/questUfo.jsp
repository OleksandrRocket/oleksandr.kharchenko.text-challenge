<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%--  <link href="static/main.css" rel="stylesheet">--%>
    <title>UFOQuest</title>
</head>

<body>
<jsp:useBean id="question" scope="session" class="ua.javarush.model.Question"/>
<jsp:useBean id="questService" scope="session" class="ua.javarush.service.QuestService"/>
<%--  <jsp:useBean id="answer" scope="session" type="ua.javarush.model.Answer"/>--%>

<h1><%= question.getText()%></h1>

<form action="/challengePage/question-<%= question.getNumber()%>" method="post">
    <br>
    <input type="radio" id="answer1" name="answer" value="true">
    <label for="answer1"><%= questService.getAnswer(question, true).getText()%>
    </label>
    <br>
    <input type="radio" id="answer2" name="answer" value="false">
    <label for="answer2"><%= questService.getAnswer(question, false).getText()%>
    </label>
    <br>
    <input type="submit" value="Відповісти">
</form>
</body>
</html>
