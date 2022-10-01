package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.demoqa.testData.UserInfo.*;

public class AuthorizationFormTest {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

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
