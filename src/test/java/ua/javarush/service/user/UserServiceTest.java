package ua.javarush.service.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.javarush.ApplicationContext;
import ua.javarush.exception.CreateUserException;
import ua.javarush.exception.InitUserRepositoryException;
import ua.javarush.exception.InvalidParamException;
import ua.javarush.exception.ReadException;
import ua.javarush.model.User;
import ua.javarush.repository.user.UserRepository;
import ua.javarush.util.UserReader;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static String name;
    private static String dateOfBirth;
    private static String email;
    private static String password;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private UserDataValidation userDataValidation;

    @Mock
    private UserReader mockUserReader;

    @InjectMocks
    private UserService userService;


    @BeforeAll
    public static void init() {
        name = "John";
        dateOfBirth = "1990-01-01";
        email = "john@example.com";
        password = "securePassword";
    }

    @Test
    public void createUserShouldReturnedNewUserWhenValidData() {

        User mockedUser = new User(name, LocalDate.parse(dateOfBirth), email, password);
        when(userDataValidation.validateUser(name, dateOfBirth, email, password)).thenReturn(mockedUser);
        User createdUser = userService.createUser(name, dateOfBirth, email, password);

        assertAll("Test checking user's params",
                () -> assertNotNull(createdUser),
                () -> assertEquals(name, createdUser.getName()),
                () -> assertEquals(LocalDate.parse(dateOfBirth), createdUser.getDateBirth()),
                () -> assertEquals(email, createdUser.getEmail()),
                () -> assertEquals(password, createdUser.getPassword())
        );
    }

    @Test
    public void createUserShouldCallValidateUserWithReceivedParams() {
        userService.createUser(name, dateOfBirth, email, password);
        Mockito.verify(userDataValidation, times(1)).validateUser(name, dateOfBirth, email, password);
    }

    @Test
    public void createUserThrowCreateUserExceptionWhenInvalidPassword() {
        when(userDataValidation.validateUser(name, dateOfBirth, email, password)).thenThrow(new InvalidParamException("Invalid password"));
        assertThrows(CreateUserException.class, () -> userService.createUser(name, dateOfBirth, email, password));
    }

    @Test
    public void createUserShouldEqualsExceptionMassageWhenInvalidPassword() {
        when(userDataValidation.validateUser(name, dateOfBirth, email, password)).thenThrow(new InvalidParamException("Invalid password"));
        try {
            User user = userService.createUser(name, dateOfBirth, email, password);
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Invalid password");
        }
    }

    @Test
    public void initShouldThrowInitUserRepositoryExceptionWhenErrorReadingJsonFile() {
        try (MockedStatic<ApplicationContext> applicationContextMock = Mockito.mockStatic(ApplicationContext.class)) {
            applicationContextMock.when(() -> ApplicationContext.getInstanceOf(any())).thenReturn(mockUserReader);
            when(mockUserReader.readJsonFile()).thenThrow(new ReadException("Exception reading"));
            assertThrows(InitUserRepositoryException.class, () -> userService.init(), "Exception reading");
            Mockito.verify(mockUserRepository, never()).save(any());
        }
    }

    @Test
    public void initShouldThrowInitUserRepositoryExceptionWhenUserListIsNull() {
        try (MockedStatic<ApplicationContext> applicationContextMock = Mockito.mockStatic(ApplicationContext.class)) {
            applicationContextMock.when(() -> ApplicationContext.getInstanceOf(any())).thenReturn(mockUserReader);
            when(mockUserReader.readJsonFile()).thenReturn(null);
            assertThrows(InitUserRepositoryException.class, () -> userService.init(), "List users cannot be null");
            Mockito.verify(mockUserRepository, never()).save(any());
        }
    }

    @Test
    public void initShouldThrowInitUserRepositoryExceptionWhenUserListIsEmpty() {
        try (MockedStatic<ApplicationContext> applicationContextMock = Mockito.mockStatic(ApplicationContext.class)) {
            applicationContextMock.when(() -> ApplicationContext.getInstanceOf(any())).thenReturn(mockUserReader);
            when(mockUserReader.readJsonFile()).thenReturn(Collections.emptyList());
            assertThrows(InitUserRepositoryException.class, () -> userService.init(), "List users cannot be empty");
            Mockito.verify(mockUserRepository, never()).save(any());
        }
    }

    @Test
    public void initShouldReturnInstanceRepositoryWithWhenUserListIsNotEmpty() {
        try (MockedStatic<ApplicationContext> applicationContextMock = Mockito.mockStatic(ApplicationContext.class)) {
            applicationContextMock.when(() -> ApplicationContext.getInstanceOf(any())).thenReturn(mockUserReader);
            User mockedUser = new User(name, LocalDate.parse(dateOfBirth), email, password);
            when(mockUserRepository.getAllUser()).thenReturn(Collections.singletonList(mockedUser));
            List<User> users = Collections.singletonList(mockedUser);
            when(mockUserReader.readJsonFile()).thenReturn(users);
            UserRepository init = userService.init();
            Mockito.verify(mockUserRepository, times(1)).save(mockedUser);
            assertIterableEquals(users, init.getAllUser());
        }
    }

    @Test
    public void createShouldSaveUserWhenValidData() {

        User mockedUser = new User(name, LocalDate.parse(dateOfBirth), email, password);
        userService.create(mockedUser);
        Mockito.verify(mockUserRepository, times(1)).save(mockedUser);
    }

    @Test
    public void deleteByIdShouldDeleteUserWhenValidId() {

        User mockedUser = new User(name, LocalDate.parse(dateOfBirth), email, password);
        when(mockUserRepository.getById(any())).thenReturn(Optional.of(mockedUser));
        doNothing().when(mockUserRepository).deleteById(any());
        userService.deleteById(1);
        Mockito.verify(mockUserRepository, times(1)).getById(1);
        Mockito.verify(mockUserRepository, times(1)).deleteById(1);
    }

    @Test
    public void getAllUsersShouldReturnListOfUsers() {

        User mockedUser = new User(name, LocalDate.parse(dateOfBirth), email, password);
        when(mockUserRepository.getAllUser()).thenReturn(Collections.singletonList(mockedUser));
        List<User> allUsers = userService.getAllUsers();
        Mockito.verify(mockUserRepository, times(1)).getAllUser();
        assertTrue(allUsers.contains(mockedUser));
    }
}
