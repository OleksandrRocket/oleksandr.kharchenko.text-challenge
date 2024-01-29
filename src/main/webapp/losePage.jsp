<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lose</title>
    <link rel="stylesheet" href="sad_css.css">
</head>

<body>
<jsp:useBean id="question" scope="session" class="ua.javarush.model.Question"/>
<jsp:useBean id="questService" scope="session" class="ua.javarush.service.question.QuestService"/>

<%= questService.getAnswer(question, false).getFinalText()%>

<form action="/userPage.jsp" method="post">
    <button>Play again</button>
</form>

</body>
</html>
