package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private static final Faker FAKER = new Faker(new Locale("en"));
    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }




    public static String generateName() {
        return  FAKER.name().username();

    }
    public static String generatePassword() {
        return  FAKER.internet().password();

    }
    public static AuthInfo generateRandomUser() {
        return  new AuthInfo(generateName(),generatePassword());

    }
    public static VerifivationCode generateRandomVerifivationCode() {
        return  new VerifivationCode(FAKER.numerify("######"));

    }
    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerifivationCode {
        private String code;
    }

    @Value
    public static class CardInfo {
        String cardNuber;
        String testId;
    }
}


