package ua.javarush.repository.user;

import ua.javarush.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> getById(Integer id);

    List<User> getAllUser();

    void deleteById(Integer id);
}
