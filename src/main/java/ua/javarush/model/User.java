package ua.javarush.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private LocalDate dateBirth; /*возраст расчитівается в рамках логики. Создание обюекта метод of*/
    private String email;
}

