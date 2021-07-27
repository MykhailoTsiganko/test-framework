package org.example.config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:${env.config.path}",
        "file:${common.config.path}",
        "system:properties"
})
public interface ConfigProps extends Config {
    @Key("web.url")
    String webUrl();

    @Key("api.url")
    String apiUrl();

    @Key("driver.timeout")
    long driverTimeout();

    @Key("driver.type")
    String driverType();

    @Key("user.login")
    String userLogin();

    @Key("user.password")
    String userPassword();

    @Key("browser.headless")
    boolean browserHeadless();


}