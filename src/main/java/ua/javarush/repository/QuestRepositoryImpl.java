package ua.javarush.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.javarush.model.Answer;
import ua.javarush.model.Question;
import ua.javarush.repository.QuestRepository;

import java.util.List;
import java.util.Optional;

@Data
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
        return allQuestions;
    }

    @Override
    public Optional<Question> findByNumber(int number) {
        return allQuestions.stream()
                .filter(question -> question.getNumber() == number)
                .findFirst();
    }
}
