<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quests</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
</head>

<body>
<jsp:useBean id="question" scope="session" class="ua.javarush.model.Question"/>
<jsp:useBean id="questService" scope="session" class="ua.javarush.service.question.QuestService"/>

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
    <input type="submit" value="Give an answer">
</form>
</body>
</html>
