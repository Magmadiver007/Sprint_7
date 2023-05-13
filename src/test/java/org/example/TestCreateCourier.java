package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.example.TestData.*;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class TestCreateCourier {
    private CourierClient courierClient;
    private int courierID;
    private Courier courier;
    private int statusCodeExpected;
    boolean isCourierCreated;
    private String errorMessage;

    public TestCreateCourier(Courier courier, int statusCodeExpected, String errorMessage) {
        this.courier = courier;
        this.statusCodeExpected = statusCodeExpected;
        this.errorMessage = errorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getFaqVarians() {
        return new Object[][]{
                {CourierGenerator.getRandom(), 201, ""},
                {CourierGenerator.getRandomNoLogin(), 400, ERROR_MESSAGE_NOT_ENOUGH_DATA},
                {CourierGenerator.getRandomNoPassword(), 400, ERROR_MESSAGE_NOT_ENOUGH_DATA},
                {CourierGenerator.getRandomNoFirstName(), 201, ""},
        };
    }

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

// Как то мне это не нравится но красивее реализации в голову не пришло...
    @Test
    @DisplayName("Test создания курьера")
    @Description("Тест создания курьера")
    public void createCourierTest() {
        ValidatableResponse createResponse = courierClient.create(courier);
        createResponse.assertThat().statusCode(equalTo(statusCodeExpected));
        int statusCode = createResponse.extract().statusCode();
        switch (statusCode) {
            case (201):
                createResponse.assertThat().body("ok", equalTo(true));
                isCourierCreated = createResponse.extract().path("ok");
                ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
                courierID = loginResponse.extract().path("id");
                break;
            case (400):
                isCourierCreated = false;
                createResponse.assertThat().body("message", equalTo(errorMessage));
                break;
            default:
                isCourierCreated = false;
                break;
        }
    }

    @After
    public void cleanUp() {
        if (isCourierCreated) {
            courierClient.delete(courierID);
        }
    }
}