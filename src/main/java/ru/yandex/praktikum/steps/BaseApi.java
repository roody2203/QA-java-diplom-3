package ru.yandex.praktikum.steps;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseApi {
    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://stellarburgers.nomoreparties.site/")
            .setContentType(ContentType.JSON)
            .build();
}
