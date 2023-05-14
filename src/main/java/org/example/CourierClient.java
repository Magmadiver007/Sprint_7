package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
public class CourierClient extends RestClient{
    private static final String COURIER_PATH = "/api/v1/courier";
    @Step("Создаем курьера")
    public ValidatableResponse createCourier(Courier courier){
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();

    }
    @Step("Выполняем log-in курьера")
    public ValidatableResponse loginCourier(CourierCredentials credentials){
        return given()
                .spec(getBaseSpec())
                .when()
                .body(credentials)
                .post("/api/v1/courier/login")
                .then();
    }
    @Step("Удаляем курьера")
    public ValidatableResponse deleteCourier(int id){
        return given()
                .spec(getBaseSpec())
                .when()
                .delete("/api/v1/courier/"+id)
                .then();
    }
}
