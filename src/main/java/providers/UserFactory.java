package providers;

import com.github.javafaker.Faker;
import models.User;

import java.text.SimpleDateFormat;

public class UserFactory {
    public User getRandomUser() {
        SimpleDateFormat correctFormat = new SimpleDateFormat("dd/MM/yyyy");
        Faker faker = new Faker();
        return new User.Builder()
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password(5, 15))
                .setBirthDate(correctFormat.format(faker.date().birthday()))
                .build();
    }

    public User getAlreadyRegisteredUser() {
        return new User.Builder()
                .setFirstName(System.getProperty("firstName"))
                .setLastName(System.getProperty("lastName"))
                .setEmail(System.getProperty("email"))
                .setPassword(System.getProperty("password"))
                .setBirthDate(System.getProperty("birthDate"))
                .build();
    }

    public User getAlreadyRegisteredUserCredentials() {
        String email = System.getProperty("email");
        String password = System.getProperty("password");
        return new User.Builder()
                .setEmail(email)
                .setPassword(password)
                .build();
    }
}
