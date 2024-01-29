package ua.javarush.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ua.javarush.exception.ReadException;
import ua.javarush.model.User;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class UserReader {

    String pathJson;

    UserReadAdapter userReadAdapter;

    public UserReader(UserReadAdapter userReadAdapter, String pathJson){
        this.pathJson = pathJson;
        this.userReadAdapter = userReadAdapter;
    }
    public List <User> readJsonFile (){
        System.out.println("readJsonFile() is called");
        try (FileReader reader = new FileReader(pathJson)){
            Gson gson = new GsonBuilder().registerTypeAdapter(User.class, userReadAdapter).create();
            Type listType = new TypeToken <List<User>> (){}.getType();
            List<User> users = gson.fromJson(reader, listType);
            return users;
        } catch (IOException e) {
            throw new ReadException("Exception reading users.json");
        }
    }
}
