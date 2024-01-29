package ua.javarush.servlet.question;

import lombok.extern.log4j.Log4j2;
import ua.javarush.service.question.CheckRightAnswerService;
import ua.javarush.service.question.QuestService;
import ua.javarush.model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/challengePage/*")
@Log4j2
public class ContinueChallengeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession();
        String value = req.getParameter("answer");
        boolean isRight = Boolean.parseBoolean(value);
        Question question = extractQuestion(currentSession);
        QuestService questService = extractQuestService(currentSession);
        CheckRightAnswerService checkRightAnswerService = CheckRightAnswerService.builder()
                .withQuestService(questService)
                .withQuestion(question)
                .withIsRight(isRight)
                .build()
                .analyzeAnswer();
        currentSession.setAttribute("questService", checkRightAnswerService.getQuestService());
        currentSession.setAttribute("question", checkRightAnswerService.getQuestion());
        req.getRequestDispatcher(checkRightAnswerService.getPage()).forward(req, resp);
    }

    private Question extractQuestion(HttpSession currentSession) {
        Object questionAttribute = currentSession.getAttribute("question");
        if (Question.class != questionAttribute.getClass()) {
            currentSession.invalidate();
            throw new RuntimeException("Session is broken");
        }
        return (Question) questionAttribute;
    }

    private QuestService extractQuestService(HttpSession currentSession) {
        Object questServiceAttribute = currentSession.getAttribute("questService");
        if (QuestService.class != questServiceAttribute.getClass()) {
            currentSession.invalidate();
            throw new RuntimeException("Session is broken");
        }
        return (QuestService) questServiceAttribute;
    }
}
