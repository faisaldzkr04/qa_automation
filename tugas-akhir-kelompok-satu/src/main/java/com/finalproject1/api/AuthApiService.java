package com.finalproject1.api;

import com.finalproject1.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class AuthApiService {

    public AuthApiService() {
        RestAssured.baseURI = ConfigReader.getProperty("api.base.url", "https://magang.dikahadir.com");
    }

    public Response login(String email, String password, String endpoint) {
        Map<String, String> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(endpoint);
    }
}
