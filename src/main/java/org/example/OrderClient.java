package org.example;

import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
public class OrderClient extends RestClient{
    private static final String ORDER_PATH = "/api/v1/orders/";

    public ValidatableResponse create(Order order){

        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();

    }
    public ValidatableResponse getOrders(int courierId){

        return given()
                .spec(getBaseSpec())
                .queryParam("courierId",courierId)
                .get(ORDER_PATH)
                .then();

    }

    public ValidatableResponse getOrderIDByTrack(int track){

        return given()
                .spec(getBaseSpec())
                .queryParam("track",track)
                .get(ORDER_PATH)
                .then();

    }
    public ValidatableResponse acceptOrder(int OrderID,int courierID){

        return given()
                .spec(getBaseSpec())
                .queryParam("courierID",courierID)
                .get(ORDER_PATH+OrderID)
                .then();

    }
}
