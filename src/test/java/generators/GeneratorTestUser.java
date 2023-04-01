package generators;

import jsonobjects.user.User;
import com.github.javafaker.Faker;

public class GeneratorTestUser {

    public static String name() { return new Faker().name().username();}
    public static String password() { return new Faker().internet().password(1,10);}
    public static String email() { return new Faker().internet().emailAddress();}

    public static User getValidUser() {
        return new User(name(), password(), email());
    }

    public static User getWithoutPassword() {
        return new User(name(), null, email());
    }

    public static User getWithoutName() {
        return new User(null, password(), email());
    }

    public static User getWithoutEmail() {
        return new User(name(), password(), null);
    }
}
