package org.example;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class TestGetOrders {
    private CourierClient courierClient;
    boolean isCourierCreated;
    private OrderClient orderClient;
    private int courierID;
    private Courier courier;
    private Order order;
    private int track;
    private int OrderID;
    @Before
    public void setUp() {
        courier = CourierGenerator.getRandom();
        courierClient = new CourierClient();
        courierClient.create(courier);
        isCourierCreated = true;
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        courierID = loginResponse.extract().path("id");
        orderClient = new OrderClient();
        order = OrderGenerator.getOrder(ColorArray.getListBlack());
        ValidatableResponse createOrderResponse = orderClient.create(order);
        track = createOrderResponse.extract().path("track");
        ValidatableResponse getOrder = orderClient.getOrderIDByTrack(track);
        OrderID = getOrder.extract().path("order.id");
        orderClient.acceptOrder(OrderID,courierID);

    }
    @Test
    public void TestOrderCreate (){
        ValidatableResponse createResponse = orderClient.getOrders(courierID);
        createResponse.assertThat().statusCode(equalTo(200));

    }

    @After
    public void cleanUp() {
        if (isCourierCreated) {
            courierClient.delete(courierID);
        }
    }
}
