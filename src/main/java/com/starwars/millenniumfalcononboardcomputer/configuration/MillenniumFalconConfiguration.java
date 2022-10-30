package com.starwars.millenniumfalcononboardcomputer.configuration;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(
        value = "classpath:millennium-falcon.json",
        factory = JsonPropertySourceFactory.class)
@Data
@ConfigurationProperties
public class MillenniumFalconConfiguration {
    private int autonomy;
    private String departure;
    private String arrival;
    @JsonAlias("routes_db")
    private String routesDb;
}
