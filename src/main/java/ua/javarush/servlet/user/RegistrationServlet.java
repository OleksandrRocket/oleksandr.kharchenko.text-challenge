package ua.javarush.servlet.user;

import lombok.extern.log4j.Log4j2;
import ua.javarush.ApplicationContext;
import ua.javarush.model.User;
import ua.javarush.service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@Log4j2
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    UserService userService = ApplicationContext.getInstanceOf(UserService.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String name = req.getParameter("name");
        String dateBirth = req.getParameter("dateBirth");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userService.createUser(name, dateBirth, email, password);
        userService.create(user);
        req.setAttribute("loggedUser", user);
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}
