package ua.javarush.servlet.user;

import ua.javarush.ApplicationContext;
import ua.javarush.model.User;
import ua.javarush.service.user.CheckLoginService;
import ua.javarush.service.user.UserService;
import ua.javarush.util.UserReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/login/*")
public class LoginServlet extends HttpServlet {

    private CheckLoginService checkLoginService = ApplicationContext.getInstanceOf(CheckLoginService.class);
    private UserService userService = ApplicationContext.getInstanceOf(UserService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       HttpSession session = req.getSession();
//        String name = req.getParameter("name");
//        LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"));
        userService.init();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = checkLoginService.checkData(email, password);
        resp.setStatus(200);
        session.setAttribute("user", user);
        req.getRequestDispatcher("userPage.jsp").forward(req,resp);

    }
}
