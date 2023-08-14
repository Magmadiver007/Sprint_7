package org.example;

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
public class TestLoginCourier {
    private CourierClient courierClient;
    private int courierID;
    private int statusCodeExpected;
    boolean isCourierCreated;
    private String errorMessage;
    private CourierCredentials credentials;

    public TestLoginCourier(int statusCodeExpected, String errorMessage, CourierCredentials credentials) {
        this.statusCodeExpected = statusCodeExpected;
        this.errorMessage = errorMessage;
        this.credentials = credentials;
    }
    @Parameterized.Parameters
    public static Object[][] getFaqVarians() {
        return new Object[][]{
                {200, "", CourierCredentials.from(CourierGenerator.getSpecific())},
                {400, ERROR_MESSAGE_NOT_ENOUGH_DATA_FOR_LOGIN, CourierCredentials.noLoginFrom(CourierGenerator.getSpecific())},
                {400, ERROR_MESSAGE_NOT_ENOUGH_DATA_FOR_LOGIN, CourierCredentials.noPasswordFrom(CourierGenerator.getSpecific())},
                {404, ERROR_MESSAGE_NOT_DATA_FOUND, CourierCredentials.from(CourierGenerator.getSpecific())},
        };
    }

    @Before
    public void setUp() {
        Courier courier = CourierGenerator.getSpecific();
        courierClient = new CourierClient();
        if (statusCodeExpected != 404) {
            courierClient.createCourier(courier);
            isCourierCreated = true;
            ValidatableResponse login = courierClient.loginCourier(CourierCredentials.from(courier));
            courierID = login.extract().path("id");
        } else {
            isCourierCreated = false;
        }

    }
    @Test
    @DisplayName("Test логин курьера")
    public void loginCourier(){
        ValidatableResponse loginResponse = courierClient.loginCourier(credentials);
        loginResponse.assertThat().statusCode(equalTo(statusCodeExpected));
        if (loginResponse.extract().statusCode() == 200) {
            loginResponse.assertThat().body("id", equalTo(courierID));
        } else {
            loginResponse.assertThat().body("message", equalTo(errorMessage));
        }

    }

    @After
    public void cleanUp() {
        if (isCourierCreated) {
            courierClient.deleteCourier(courierID);
        }
    }
}
