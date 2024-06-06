package data.factory;

import com.github.javafaker.Faker;
import models.UserModel;

import java.util.Locale;


public class UserData {
    public UserModel user = new UserModel();
    Faker faker = new Faker(new Locale("en_Us"));

    public UserModel userCreationData()
    {
        user.setId(faker.number().randomDigitNotZero());
        user.setUsername(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setPhone(faker.phoneNumber().phoneNumber());
        user.setUserStatus("1");
        return user;
    }
}
