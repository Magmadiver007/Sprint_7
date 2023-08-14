package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
public class OrderClient extends RestClient{
    private static final String ORDER_PATH = "/api/v1/orders";

    @Step("Создаем новый заказ")
    public ValidatableResponse createOrder(Order order){

        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();

    }
    @Step("Получаем список заказов назначенных для курьера")
    public ValidatableResponse getOrders(int courierId){

        return given()
                .spec(getBaseSpec())
                .queryParam("courierId",courierId)
                .get(ORDER_PATH)
                .then();

    }
    @Step("Получаем данные о заказе по параметру track")
    public ValidatableResponse getOrderIDByTrack(int track){

        return given()
                .spec(getBaseSpec())
                .queryParam("t",track)
                .get(ORDER_PATH+"/track")
                .then();

    }
    @Step("Принимаем курьером заказ")
    public ValidatableResponse acceptOrder(int OrderID,int courierID){

        return given()
                .spec(getBaseSpec())
                .queryParam("courierId",courierID)
                .put(ORDER_PATH+"/accept/"+OrderID)
                .then();

    }
}
