package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class TestCreateOrder {
    private OrderClient orderClient;
    private Order order;
    private int track;
    public TestCreateOrder (Order order){
        this.order = order;
    }


    @Parameterized.Parameters
    public static Object[][] getOrderVariants() {
        return new Object[][]{
                {OrderGenerator.getOrder(ColorArray.getListBlack())},
                {OrderGenerator.getOrder(ColorArray.getListGrey())},
                {OrderGenerator.getOrder(ColorArray.getListBlackAndGrey())},
                {OrderGenerator.getOrder(null)},
        };
    }
    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }
    @Test
    @DisplayName("Test заказа")
    @Description("Создаем заказы, проверяем track")
    public void testOrderCreate (){
        ValidatableResponse createResponse = orderClient.createOrder(order);
        createResponse.assertThat().statusCode(equalTo(201));
        createResponse.assertThat().body("track", notNullValue());
        track = createResponse.extract().path("track");
    }

}
