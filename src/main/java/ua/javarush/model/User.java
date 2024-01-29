package ua.javarush.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;
    private LocalDate dateBirth;
    private String email;
    private String password;

}
