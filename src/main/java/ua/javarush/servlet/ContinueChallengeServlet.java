package ua.javarush.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.javarush.service.CheckService;
import ua.javarush.service.QuestService;
import ua.javarush.model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/challengePage/*")
public class ContinueChallengeServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(ContinueChallengeServlet.class.getSimpleName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession currentSession = req.getSession();
        String value = req.getParameter("answer");
        boolean isRight = Boolean.parseBoolean(value);
        LOGGER.debug(isRight);
        Question question = extractQuestion(currentSession);
        QuestService questService = extractQuestService(currentSession);
        CheckService checkService = CheckService.builder()
                .withQuestService(questService)
                .withQuestion(question)
                .withIsRight(isRight)
                .build()
                .analyzeAnswer();
        currentSession.setAttribute("questService", checkService.getQuestService());
        currentSession.setAttribute("question", checkService.getQuestion());
        req.getRequestDispatcher(checkService.getPage()).forward(req, resp);
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
