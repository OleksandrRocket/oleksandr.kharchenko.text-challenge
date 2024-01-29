package ua.javarush.service.user;

import lombok.extern.log4j.Log4j2;
import ua.javarush.ApplicationContext;
import ua.javarush.exception.CreateUserException;
import ua.javarush.exception.InitUserRepositoryException;
import ua.javarush.exception.InvalidParamException;
import ua.javarush.exception.ReadException;
import ua.javarush.exception.UserNotFoundException;
import ua.javarush.model.User;
import ua.javarush.repository.user.UserRepository;
import ua.javarush.util.UserReader;

import java.util.List;
import java.util.Objects;

@Log4j2
public class UserService {

    private UserRepository userRepository;

    private UserDataValidation userDataValidation;

    public UserService(UserRepository userRepository, UserDataValidation userDataValidation) {
        this.userDataValidation = userDataValidation;
        this.userRepository = userRepository;
    }

    public User createUser(String name, String dateOfBirth, String email, String password) {
        log.info("Checking user data for " + name);
        try {
            User user = userDataValidation.validateUser(name, dateOfBirth, email, password);
            log.info("User " + name + " has been created");
            return user;
        } catch (InvalidParamException exception) {
            log.error("Failed to create user due to invalid data for " + name, exception.getMessage());
            throw new CreateUserException(exception.getMessage());
        }
    }

    public UserRepository init() {
        UserReader userReader = ApplicationContext.getInstanceOf(UserReader.class);
        List<User> users;
        try {
            log.error("reading JSON file");
            users = userReader.readJsonFile();
        } catch (ReadException ex) {
            log.error("Error reading JSON file", ex);
            throw new InitUserRepositoryException(ex.getMessage());
        }
        if (Objects.isNull(users)) {
            throw new InitUserRepositoryException("List users cannot be null");
        }
        if (users.isEmpty()) {
            throw new InitUserRepositoryException("List users cannot be empty");
        }
        users.stream()
                .forEach(user -> userRepository.save(user));
        return userRepository;
    }

    public void create(User user) {
        log.info("User received: " + user.getName());
        userRepository.save(user);
    }

    public void deleteById(Integer id) {
        userRepository.getById(id).orElseThrow(() -> new UserNotFoundException(
                String.format("User with id = %d not found!", id)
        ));
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        log.info(String.format("Get all users!"));
        return userRepository.getAllUser();
    }
}
