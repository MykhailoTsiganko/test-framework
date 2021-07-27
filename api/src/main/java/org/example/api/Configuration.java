package org.example.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class Configuration {
    private String baseUrl;
    private String servicePath;
    private Map<String, String> headers;
}
