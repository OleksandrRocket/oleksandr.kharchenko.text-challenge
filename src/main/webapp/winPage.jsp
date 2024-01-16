<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<jsp:useBean id="question" scope="session" class="ua.javarush.model.Question"/>
<jsp:useBean id="questService" scope="session" class="ua.javarush.service.QuestService"/>

<%= questService.getAnswer(question, true).getFinalText()%>

<form action="/index.jsp" method="post">
    <button>Зіграти ще раз</button>
</form>

</body>
</html>
