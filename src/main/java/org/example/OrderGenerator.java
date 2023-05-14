package org.example;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

public class OrderGenerator {

    public static Order getOrder(List<String> color){
        final String firstName = "Тест";
        final String lastName = "Тестовский";
        final String address = "Тестовая улица 42";
        final String metroStation = "Тест";
        final String phone = "+79898989898";
        final String rentTime = "3";
        final String deliveryDate = "2023-07-07";
        final String comment = "Хочу кататься!";

        return new Order(firstName, lastName, address, metroStation,phone,rentTime,deliveryDate,comment,color);
    }
}
