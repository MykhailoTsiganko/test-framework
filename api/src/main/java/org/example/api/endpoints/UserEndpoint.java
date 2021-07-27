package org.example.api.endpoints;

import com.google.common.collect.ImmutableMap;
import org.example.api.Configuration;
import org.example.api.ResponseWrapper;
import org.example.api.RestClient;
import org.example.config.ConfigProvider;
import io.qameta.allure.Step;

import java.util.Map;

public class UserEndpoint  extends RestClient {
    @Override
    protected Configuration defaultConfiguration() {
        return Configuration.builder()
                .baseUrl(ConfigProvider.CONFIG_PROPS.apiUrl())
                .headers(Map.of())
                .servicePath("/v2/pet")
                .build();
    }

    @Step("Get by Status")
    public ResponseWrapper<Configuration> getByStatus(){
        return get("/findByStatus", ImmutableMap.<String, String>builder().put("status","aveilable").build(), Configuration.class);
    }
}
