package org.example.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public abstract class RestClient {

    public RestClient(){

    }


    private RequestSpecification requestSpecification;

    protected abstract Configuration defaultConfiguration();


    private RequestSpecification getRequestSpecification() {
        if (requestSpecification == null) {
            requestSpecification = new RequestSpecBuilder()
                    .setBaseUri(defaultConfiguration().getBaseUrl())
                    .setBasePath(defaultConfiguration().getServicePath())
                    .setContentType(ContentType.JSON)
                    .addHeaders(defaultConfiguration().getHeaders())
                    .log(LogDetail.ALL)
                    .addFilter(new AllureRestAssured())
                    .build();
        }
        return requestSpecification;
    }

    private void logResponse(Response response) {
        response.then().log().status();
        response.then().log().ifStatusCodeMatches(Matchers.greaterThan(300));
    }

    public <F> ResponseWrapper<F> get(String path, Class<F> responseClass) {
        Response response = given().spec(getRequestSpecification()).get(path);
        logResponse(response);

        return new ResponseWrapper<>(response, responseClass);
    }

    public Response get(String path) {
        Response response = given().spec(getRequestSpecification()).get(path);
        logResponse(response);

        return response;
    }

    public <F> ResponseWrapper<F> get(String path, Map<String, String> queryParams, Class<F> responseClass) {
        Response response = given().spec(getRequestSpecification()).queryParams(queryParams).get(path);
        logResponse(response);

        return new ResponseWrapper<>(response, responseClass);
    }

    public <F> ResponseWrapper<F> get(String path, Class<F> responseClass, String parameterKey, List parameterValues,
                                      String parameterKey1, List parameterValues1) {
        Response response = given().spec(getRequestSpecification()).queryParams(parameterKey, parameterValues)
                .queryParams(parameterKey1, parameterValues1).get(path);
        logResponse(response);

        return new ResponseWrapper<>(response, responseClass);
    }

    public <T, F> ResponseWrapper<F> post(String path, T payload, Class<F> responseClass) {
        Response response = given().spec(getRequestSpecification()).body(payload).post(path);
        logResponse(response);

        return new ResponseWrapper<>(response, responseClass);
    }

    public <F> ResponseWrapper<F> post(String path, Map<String, String> payload, Class<F> responseClass) {
        Response response = given().spec(getRequestSpecification()).body(payload).post(path);
        logResponse(response);

        return new ResponseWrapper<>(response, responseClass);
    }

    public <T, F> ResponseWrapper<F> post(String path, Map<String, String> queryParams, T payload, Class<F> responseClass) {
        Response response = given().spec(getRequestSpecification()).queryParams(queryParams).body(payload).post(path);
        logResponse(response);

        return new ResponseWrapper<>(response, responseClass);
    }

    public <F> ResponseWrapper<F> post(String path, Class<F> responseClass) {
        Response response = given().spec(getRequestSpecification()).post(path);
        logResponse(response);

        return new ResponseWrapper<>(response, responseClass);
    }

    public <F> ResponseWrapper<F> post(String path, File file, Class<F> responseClass) {
        Response response = given().baseUri(defaultConfiguration().getBaseUrl())
                .basePath(defaultConfiguration().getServicePath())
                .headers(defaultConfiguration().getHeaders())
                .multiPart(file)
                .post(path);
        logResponse(response);

        return new ResponseWrapper<>(response, responseClass);
    }

    public <T, F> ResponseWrapper<F> patch(String path, T payload, Class<F> responseClass) {
        Response response = given().body(payload).patch(path);
        logResponse(response);

        return new ResponseWrapper<>(response, responseClass);
    }

    public <T, F> ResponseWrapper<F> put(String path, T payload, Class<F> responseClass) {
        Response response = given().spec(getRequestSpecification()).body(payload).put(path);
        logResponse(response);

        return new ResponseWrapper<>(response, responseClass);
    }

    public <F> ResponseWrapper<F> put(String path, Class<F> responseClass) {
        Response response = given().spec(getRequestSpecification()).put(path);
        logResponse(response);

        return new ResponseWrapper<>(response, responseClass);
    }

    public <F> ResponseWrapper<F> delete(String path, Class<F> responseClass) {
        Response response = given().spec(getRequestSpecification()).log().method().delete(path);
        logResponse(response);

        return new ResponseWrapper<>(response, responseClass);
    }
}
