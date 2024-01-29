package ua.javarush.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    private Integer id;
    private String text;
    private Integer nextQuestionNumber;
    private String finalText;
    private boolean isRight;
}
