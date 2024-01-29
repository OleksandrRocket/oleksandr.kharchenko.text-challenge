package ua.javarush.exception;

public class QuestServicIsNullException extends RuntimeException {
    public QuestServicIsNullException(String start) {
        super(start);
    }
}
