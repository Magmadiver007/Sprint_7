package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestClient {
    private static final String BASE_URL = "http://qa-scooter.praktikum-services.ru";
    protected RequestSpecification getBaseSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}
