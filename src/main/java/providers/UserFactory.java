package providers;

import com.github.javafaker.Faker;
import models.User;

import java.text.SimpleDateFormat;

public class UserFactory {
    public User getRandomUser() {
        SimpleDateFormat correctFormat = new SimpleDateFormat("dd/MM/yyyy");
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(5, 15);
        String birthDate = correctFormat.format(faker.date().birthday());
        return new User.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setBirthDate(birthDate)
                .build();
    }

    public User getAlreadyRegisteredUser() {
        String firstName = System.getProperty("firstName");
        String lastName = System.getProperty("lastName");
        String email = System.getProperty("email");
        String password = System.getProperty("password");
        String birthDate = System.getProperty("birthDate");
        return new User.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setBirthDate(birthDate)
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
