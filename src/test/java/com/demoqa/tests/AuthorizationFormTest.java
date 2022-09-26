package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import com.demoqa.utils.Month;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Locale;

public class AuthorizationFormTest {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    Faker faker = new Faker(new Locale("de"));

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().safeEmailAddress();
    String number = String.valueOf(faker.phoneNumber().subscriberNumber(10));
    String gender = faker.demographic().sex();
    String pic = "pic.png";
    String hobby = "Sports";
    String subject = "English";
    String path = "src/test/resources/pic.png";
    String address = String.valueOf(faker.address().fullAddress());
    String state = "NCR";
    String city = "Noida";
    String day = String.valueOf(faker.number().numberBetween(1, 31));
    String month = String.valueOf(Month.getRandomMonth());
    String year = String.valueOf(faker.number().numberBetween(1950, 2010));
    String date = day + " " + month + "," + year;

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setNumber(number)
                .setBirthDate(day, month, year)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadFile(path)
                .setAddress(address)
                .setStateAndCity(state, city)
                .submit();

        registrationFormPage.checkResultTableVisibility()
                .checkResultTableData(firstName, lastName, email, number, date, gender,
                                        pic, address, subject, hobby, state, city);
    }

    @Test
    void fillFormWithMinimumTest() {
        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setNumber(number)
                .submit();
        registrationFormPage.checkResultTableVisibility()
                .checkResultTableData(firstName, lastName, number, gender);
    }
}
