package models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthDate;

    public User(String firstName, String lastName, String email, String password, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
//
//    public User(Builder builder) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.birthDate = birthDate;
//    }

    @Override
    public String toString() {
        return "User [first name: " + firstName + ", last name: " + lastName + ", email: " + email
                + ", password: " + password + ", date of birth: " + birthDate + "]";
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String birthDate;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String firstName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String firstName) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String firstName) {
            this.password = password;
            return this;
        }

        public Builder setBirthDate(String firstName) {
            this.birthDate = birthDate;
            return this;
        }

        public User build() {
            return new User(firstName, lastName, email, password, birthDate);
//            return new User(this);
        }

    }
}
