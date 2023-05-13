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


public class TestCreateCourierDouble {
    private CourierClient courierClient;
    private int courierID;
    private Courier courier;
    private int statusCodeExpected;
    boolean isCourierCreated;
    private String errorMessage;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getSpecific();
    }

    @Test
    @DisplayName("Test создания дубля курьера")
    @Description("Особый случай создания курьера - дубль")
    public void createCourierTest() {
        ValidatableResponse createResponseSuccess = courierClient.create(courier);

        int statusCode = createResponseSuccess.extract().statusCode();

        if (statusCode == 201){
            ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
            isCourierCreated = createResponseSuccess.extract().path("ok");
            courierID = loginResponse.extract().path("id");

            ValidatableResponse createResponseDouble = courierClient.create(courier);
            createResponseDouble.assertThat().statusCode(equalTo(409)).and().body("message", equalTo(ERROR_MESSAGE_DOUBLE));
        } else {
            isCourierCreated = false;
        }



    }

    @After
    public void cleanUp() {
        if (isCourierCreated) {
            courierClient.delete(courierID);
        }
    }
}
