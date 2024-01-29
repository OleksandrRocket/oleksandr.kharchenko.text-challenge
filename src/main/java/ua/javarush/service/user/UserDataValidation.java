package ua.javarush.service.user;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ua.javarush.exception.InvalidParamException;
import ua.javarush.model.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.regex.Pattern;

@Log4j2
@NoArgsConstructor
public class UserDataValidation {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_DATE_OF_BIRTH_REGEX =
            Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    public User validateUser(String name, String dateOfBirth, String email, String password) {
        return new User(validateName(name), validateDate(dateOfBirth), validateEmail(email), validatePassword(password));
    }

    private String validatePassword(String password) {
        if (Objects.isNull(password)) {
            log.warn("User's password is null");
            throw new InvalidParamException("User's password is null");
        } else if (password.isEmpty()) {
            log.warn("User's password is empty");
            throw new InvalidParamException("User's password is empty");
        } else
            log.info("User's password validated");
        return password;
    }

    private String validateName(String name) {
        if (Objects.isNull(name)) {
            log.warn("User's name is null");
            throw new InvalidParamException("User's name is null");
        } else if (name.isEmpty()) {
            log.warn("User name is empty");
            throw new InvalidParamException("User name is empty");
        } else
            log.info("User name validated");
        return name;
    }

    private String validateEmail(String email) {
        if (Objects.isNull(email)) {
            log.warn("User's email is null");
            throw new InvalidParamException("User's email is null");
        } else if (email.isEmpty()) {
            log.warn("User email is empty");
            throw new InvalidParamException("User email is empty");
        } else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches()) {
            log.warn("User email is invalid");
            throw new InvalidParamException("User email is invalid");
        } else
            log.info("User email validated");
        return email;
    }

    private LocalDate validateDate(String dateOfBirth) {
        LocalDate dateBirth;
        if (Objects.isNull(dateOfBirth)) {
            log.warn("User's dateOfBirth is null");
            throw new InvalidParamException("User's dateOfBirth is null");
        } else if (dateOfBirth.isEmpty()) {
            log.warn("User dateOfBirth is empty");
            throw new InvalidParamException("User dateOfBirth is empty");
        } else if (!VALID_DATE_OF_BIRTH_REGEX.matcher(dateOfBirth).matches()) {
            log.warn("User dateOfBirth is not like the format");
            throw new InvalidParamException("User dateOfBirth is not like the format");
        } else {
            LocalDate currentDate = LocalDate.now();
            dateBirth = LocalDate.parse(dateOfBirth);
            int years = Period.between(dateBirth, currentDate).getYears();
            if (years < 5 || years > 100) {
                log.warn("User's age is illegal");
                throw new InvalidParamException("User's age is illegal");
            } else
                log.info("User dateOfBirth validated");
            return dateBirth;
        }
    }
}
