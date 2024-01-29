package ua.javarush;

import ua.javarush.exception.ApplicationContextInstanceCreateException;
import ua.javarush.repository.user.UserRepositoryImpl;
import ua.javarush.service.question.QuestService;
import ua.javarush.service.user.CheckLoginService;
import ua.javarush.service.user.UserDataValidation;
import ua.javarush.service.user.UserService;
import ua.javarush.util.UserReadAdapter;
import ua.javarush.util.UserReader;

import java.util.HashMap;
import java.util.Map;

public final class ApplicationContext {
    private static final String jsonPathUser = "src/main/resources/users.json";
    private static final UserReadAdapter USER_ADAPTER = new UserReadAdapter();
    private static final UserReader USER_READER = new UserReader(USER_ADAPTER, jsonPathUser);

    private static final QuestService QUEST_SERVICE = new QuestService();
    private static final UserDataValidation USER_DATA_VALIDATION = new UserDataValidation();

    private static final UserRepositoryImpl USER_REPOSITORY = new UserRepositoryImpl();

    private static final UserService USER_SERVICE = new UserService(USER_REPOSITORY, USER_DATA_VALIDATION);

    private static final CheckLoginService CHECK_LOGIN_SERVICE = new CheckLoginService(USER_SERVICE);


    private static final Map<Class<?>, Object> CLASS_TO_OBJECT_INSTANCE = new HashMap<>();

    static {
        CLASS_TO_OBJECT_INSTANCE.put(QuestService.class, QUEST_SERVICE);
        CLASS_TO_OBJECT_INSTANCE.put(UserService.class, USER_SERVICE);
        CLASS_TO_OBJECT_INSTANCE.put(CheckLoginService.class, CHECK_LOGIN_SERVICE);
        CLASS_TO_OBJECT_INSTANCE.put(UserReader.class, USER_READER);
        CLASS_TO_OBJECT_INSTANCE.put(UserDataValidation.class, USER_DATA_VALIDATION);

    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstanceOf(Class<T> aClass) {
        Object object = CLASS_TO_OBJECT_INSTANCE.get(aClass);
        if (object == null) {
            throw new ApplicationContextInstanceCreateException(aClass);
        }
        return (T) object;
    }
}
