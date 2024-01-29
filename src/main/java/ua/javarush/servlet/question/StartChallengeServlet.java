package ua.javarush.servlet.question;

import ua.javarush.ApplicationContext;
import ua.javarush.service.question.QuestService;
import ua.javarush.model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/challengePage")
public class StartChallengeServlet extends HttpServlet {
    private final QuestService questService = ApplicationContext.getInstanceOf(QuestService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession httpSession = req.getSession();
        String game = req.getParameter("game");
        questService.initChallenge(game);
        Question firstQuestion = questService.start().get();
        httpSession.setAttribute("questService", questService);
        httpSession.setAttribute("question", firstQuestion);
        resp.setStatus(200);
        req.getRequestDispatcher("questUfo.jsp").forward(req, resp);

    }
}
