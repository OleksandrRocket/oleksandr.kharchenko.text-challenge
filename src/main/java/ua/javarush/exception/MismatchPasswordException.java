package ua.javarush.exception;

public class MismatchPasswordException extends RuntimeException {
    public MismatchPasswordException(String passwordsDoNotMatch) {
        super(passwordsDoNotMatch);
    }
}
