package ua.javarush.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private Integer id;
    private String text;

    private Integer nextQuestionNumber; //can be null

    private String finalText;
     private boolean isRight;
}
