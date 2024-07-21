package controllers;

import configReaders.CommonConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import configReaders.UserConfigReader;

public class BaseController {
    public CommonConfigReader commonConfigReader = new CommonConfigReader();
    public final String BASE_URL_PATH = commonConfigReader.getBaseUrl() + commonConfigReader.getApiVersion();

    protected static final String HEADER_CONTENT_TYPE = "Content-Type";
    protected static final String APPLICATION_JSON = "application/json";

    protected Response postRequest(String path, Object body) {
        return RestAssured.given()
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .log().method().request()
                .log().uri()
                .log().body()
                .log().headers()
                .post(BASE_URL_PATH + path);
    }

    public Response putRequest(String path, Object body) {
        return RestAssured.given()
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .log().method().request()
                .log().uri()
                .log().body()
                .log().headers()
                .put(BASE_URL_PATH + path);
    }

    protected Response deleteRequest(String path) {
        return RestAssured.given()
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .log().method().request()
                .log().uri()
                .log().headers()
                .delete(BASE_URL_PATH + path);
    }
    public Response getRequest(String path) {
        return RestAssured.given()
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .log().method().request()
                .log().uri()
                .log().headers()
                .get(BASE_URL_PATH + path);
    }

}
