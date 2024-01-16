package ua.javarush.repository;

import ua.javarush.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestRepository {
    Optional<Question> startQuest();
    Optional<Question> continueQuest(Question question);
    List<Question> getAll();
    Optional<Question> findByNumber(int number);

}
