package ua.javarush.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private Integer number;


    private String text;
    //    private String imgURL;

   // @JsonProperty("answer1")
   // private String answer1;

    //@JsonProperty("answer2")
    //private String answer2;
      private List <Answer> answers;

}
