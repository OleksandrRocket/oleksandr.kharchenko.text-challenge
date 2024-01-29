package ua.javarush.exception;

public class ReadException extends RuntimeException {
    public ReadException (String message){
        super(message);
    }
}
