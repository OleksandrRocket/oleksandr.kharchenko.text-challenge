package ua.javarush.repository.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.javarush.model.Answer;
import ua.javarush.model.Question;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class QuestRepositoryImpl implements QuestRepository {

    private List<Question> allQuestions;

    @Override
    public Optional<Question> startQuest() {
        return findByNumber(1);
    }

    @Override
    public Optional<Question> continueQuest(Question question) {
        List<Answer> answers = question.getAnswers();
        Integer nextQuestionNumber = answers.stream()
                .filter(answer -> answer.isRight() == true)
                .findFirst()
                .get()
                .getNextQuestionNumber();
        return findByNumber(nextQuestionNumber);
    }

    @Override
    public List<Question> getAll() {
        return Collections.unmodifiableList(allQuestions);
    }

    @Override
    public Optional<Question> findByNumber(int number) {
        return allQuestions.stream()
                .filter(question -> question.getNumber() == number)
                .findFirst();
    }
}
