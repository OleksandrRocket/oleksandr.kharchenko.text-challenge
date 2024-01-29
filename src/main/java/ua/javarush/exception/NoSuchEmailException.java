package ua.javarush.exception;

public class NoSuchEmailException extends RuntimeException {
    public NoSuchEmailException(String emailIsNotExist) {
        super(emailIsNotExist);
    }
}
