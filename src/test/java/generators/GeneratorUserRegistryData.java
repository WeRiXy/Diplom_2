package generators;

import com.github.javafaker.Faker;
import jsonobjects.user.User;
import jsonobjects.user.UserRegistryData;

public class GeneratorUserRegistryData {

    public static String email() { return new Faker().internet().emailAddress();}
    public static String password() { return new Faker().internet().password(1,10);}

    public static UserRegistryData getRegistryData(String email, String password) {
        return new UserRegistryData(email, password);
    }

    public static UserRegistryData getRandomRegistryData() {
        return new UserRegistryData(email(), password());
    }

    public static UserRegistryData getRegistryDataFromUser(User user) {
        return new UserRegistryData(user.getEmail(), user.getPassword());
    }
}
