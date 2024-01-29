package ua.javarush.service.question;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import ua.javarush.exception.ReadException;
import ua.javarush.model.Answer;
import ua.javarush.model.Question;
import ua.javarush.repository.question.QuestRepository;
import ua.javarush.repository.question.QuestRepositoryImpl;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Log4j2
public class QuestService {

    private QuestRepository questRepository;

    public QuestRepository initChallenge(String game) {
        Gson g = new Gson();
        switch (game) {
            case "ufo":
                try (FileReader fileReader = new FileReader("src/main/resources/ufo.json", StandardCharsets.UTF_8)) {
                    return questRepository = g.fromJson(fileReader, QuestRepositoryImpl.class);
                } catch (IOException e) {
                    throw new ReadException("Exception reading ufo.json");
                }
            default:
                throw new IllegalArgumentException();
        }
    }

    public Optional<Question> start() {
        return questRepository.startQuest();
    }

    public Answer getAnswer(Question question, boolean isRight) {
        Integer numberQuestion = question.getNumber();
        return questRepository.getAll().stream()
                .filter(question1 -> question1.getNumber() == numberQuestion)
                .findFirst()
                .get()
                .getAnswers().stream()
                .filter(answer -> answer.isRight() == isRight)
                .findFirst()
                .get();
    }

    public Optional<Question> continueQuest(Question question) {
        return questRepository.continueQuest(question);
    }
}
