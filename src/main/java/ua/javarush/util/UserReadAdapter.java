package ua.javarush.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import ua.javarush.model.User;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserReadAdapter implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        JsonElement dateElement = jsonObject.get("dateBirth");
        LocalDate dateBirth = deserializeLocalDate(dateElement);
        String email = jsonObject.get("email").getAsString();
        String password = jsonObject.get("password").getAsString();
        return new User(name, dateBirth, email, password);
    }

    private LocalDate deserializeLocalDate(JsonElement jsonElement) {
        String dateString = jsonElement.getAsString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }
}
