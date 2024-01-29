package ua.javarush.repository.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ua.javarush.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Log4j2
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private Integer id = 0;
    private HashMap<Integer, User> db = new HashMap<>();

    @Override
    public void save(User user) {
        db.put(++this.id, user);
        log.info(String.format("user added with id: %d", id));
    }

    @Override
    public void deleteById(Integer id) {
        db.remove(id);
        log.info(String.format("Deleted user with id: %d", id));
    }

    @Override
    public List<User> getAllUser() {
        log.info(String.format("Get All users from DB!"));
        return new ArrayList<>(db.values());
    }

    @Override
    public Optional<User> getById(Integer id) {
        log.info(String.format("Get users from DB with id : %d!", id));
        return Optional.ofNullable(db.get(id));
    }
}
