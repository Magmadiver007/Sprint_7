package org.example;

import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
public class CourierClient extends RestClient{
    private static final String COURIER_PATH = "/api/v1/courier";

    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();

    }
    public ValidatableResponse login(CourierCredentials credentials){
        return given()
                .spec(getBaseSpec())
                .when()
                .body(credentials)
                .post("/api/v1/courier/login")
                .then();
    }
    public ValidatableResponse delete(int id){
        return given()
                .spec(getBaseSpec())
                .when()
                .delete("/api/v1/courier/"+id)
                .then();
    }
}
