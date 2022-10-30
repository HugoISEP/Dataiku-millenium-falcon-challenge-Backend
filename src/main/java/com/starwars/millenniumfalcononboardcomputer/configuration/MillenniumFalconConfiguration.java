package com.starwars.millenniumfalcononboardcomputer.configuration;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Component
@PropertySource(
        value = "classpath:millennium-falcon.json",
        factory = JsonPropertySourceFactory.class)
@Data
@ConfigurationProperties
@Validated
public class MillenniumFalconConfiguration {
    @Min(1)
    private int autonomy;
    @NotBlank
    private String departure;
    @NotBlank
    private String arrival;
    @NotBlank
    @JsonAlias("routes_db")
    private String routesDb;
}
