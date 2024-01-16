package ua.javarush.service;

import com.google.gson.Gson;
import ua.javarush.model.Answer;
import ua.javarush.model.Question;
import ua.javarush.repository.QuestRepository;
import ua.javarush.repository.QuestRepositoryImpl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;


public class QuestService {

    private QuestRepository questRepository;
    private Gson g = new Gson();

    public QuestRepository initChallenge(String game) {

        switch (game) {
            case "ufo":
                try (FileReader fileReader = new FileReader("D:\\devtools\\IdeaProjects\\Text-challenge\\src\\main\\resources\\UFO.json")) {
                    return questRepository = g.fromJson(fileReader, QuestRepositoryImpl.class);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
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
      return   questRepository.continueQuest(question);

    }
}


