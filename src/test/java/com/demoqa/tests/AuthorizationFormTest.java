package com.demoqa.tests;

import com.beust.jcommander.IStringConverter;
import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AuthorizationFormTest {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    String firstName = "Dinara";
    String lastName = "Gaynanova";
    String email = "gaynanovadr@gmail.ru";
    String number = "8917345678";
    String date = "31 July,1988";
    String gender = "Female";
    String pic = "pic.png";
    String hobby = "Sports";
    String subject = "English";
    String path = "src/test/resources/pic.png";
    String address = "Some address";
    String state = "NCR";
    String city = "Noida";

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
                .setBirthDate("31", "July", "1988")
                .setSubject(subject)
                .setHobby(hobby)
                .uploadFile(path)
                .setAddress(address)
                .setStateAndCity(state, city)
                .submit();

        registrationFormPage.checkResultTableVisibility()
                .checkResultTableData(firstName, lastName, email, number, date, gender, pic, address);
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
