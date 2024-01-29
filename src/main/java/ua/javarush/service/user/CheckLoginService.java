package ua.javarush.service.user;

import lombok.extern.log4j.Log4j2;
import ua.javarush.exception.MismatchPasswordException;
import ua.javarush.exception.NoSuchEmailException;
import ua.javarush.model.User;

import java.util.List;
import java.util.Optional;

@Log4j2
public class CheckLoginService {
    private final UserService USER_SERVICE;

    public CheckLoginService(UserService userService) {
        this.USER_SERVICE = userService;
    }


    public User checkData(String email, String enteredPassword) {
        log.info("Checking the entered data");
        User user = checkLogin(email);
        checkPassword(user.getPassword(), enteredPassword);
        return user;
    }

    private User checkLogin(String email) {
        log.info("Check email");
        List<User> allUsers = USER_SERVICE.getAllUsers();
        Optional<User> userByEmail = allUsers.stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny();
        if (userByEmail.isPresent()) {
            log.info("Email is present");
            return userByEmail.get();
        } else
            log.error("Email is not exist");
        throw new NoSuchEmailException("Email is not exist");
    }

    private boolean checkPassword(String passwordFromDd, String enteredPassword) {
        log.info("Check password");
        if (passwordFromDd.equals(enteredPassword)) {
            log.info("Password is correct");
            return true;
        }
        log.error("Passwords do not match");
        throw new MismatchPasswordException("Passwords do not match");
    }
}
