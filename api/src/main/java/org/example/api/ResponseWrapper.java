package org.example.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@AllArgsConstructor
public class ResponseWrapper<T> {
    private final Response response;
    private final Class<T> responseClass;

    public Response getResponse() {
        return response;
    }

    public T readEntity() {
        try {
            return new ObjectMapper().readValue(response.getBody().prettyPrint(), responseClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public T readEntityWithData() {
        return response.getBody().jsonPath().getObject("data", responseClass);
    }

    public String readEntity(String jsonPath) {
        return response.getBody().jsonPath().getString(jsonPath);
    }

    public List<T> readEntities() {
        return response.getBody().jsonPath().getList("data", responseClass);
    }

    public List<T> readEntities(String path) {
        return response.getBody().jsonPath().getList(path, responseClass);
    }

    public ResponseWrapper<T> expectingStatusCode(int statusCode) {
        assertThat(response.getStatusCode()).as("Response code differs").isEqualTo(statusCode);
        return this;
    }

    public ResponseWrapper<T> expectingContentType(String contentType) {
        assertThat(response.getContentType()).as("Content type differs").isEqualTo(contentType);
        return this;
    }
}
