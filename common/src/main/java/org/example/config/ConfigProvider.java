package org.example.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider {
    private static final String CONFIG_DIR = "src/test/resources/";
    public static final ConfigProps CONFIG_PROPS;

    static {
        Environment env = Environment.getByCommandLineProp();
        switch (env) {
            case dev:
                ConfigFactory.setProperty("env.config.path", CONFIG_DIR + "/" + env + ".properties");
                break;
            default:
                throw new RuntimeException("Provide implementation for '" + env + "' environment");
        }
        ConfigFactory.setProperty("common.config.path", CONFIG_DIR + "/common.properties");
        ConfigFactory.setProperty("testrail.config.path", CONFIG_DIR + "/testrail.properties");
        CONFIG_PROPS = ConfigFactory.create(ConfigProps.class, System.getProperties());
    }
}
