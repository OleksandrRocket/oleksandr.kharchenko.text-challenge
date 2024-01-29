package ua.javarush.exception;

public class ApplicationContextInstanceCreateException extends RuntimeException {
    public <T> ApplicationContextInstanceCreateException(Class<T> aClass) {
        super();
    }
}
