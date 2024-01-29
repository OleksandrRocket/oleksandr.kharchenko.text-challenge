package ua.javarush.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private Integer number;
    private String text;
    private List <Answer> answers;
}
