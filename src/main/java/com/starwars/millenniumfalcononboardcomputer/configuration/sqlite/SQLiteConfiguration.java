package com.starwars.millenniumfalcononboardcomputer.configuration.sqlite;

import com.starwars.millenniumfalcononboardcomputer.configuration.MillenniumFalconConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class SQLiteConfiguration {
    private final MillenniumFalconConfiguration millenniumFalconConfiguration;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        val dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);

        val absolutePath = millenniumFalconConfiguration.getRoutesDb();
        val relativePath = Objects.requireNonNull(this.getClass().getClassLoader()
                        .getResource(millenniumFalconConfiguration.getRoutesDb()))
                .getPath().replace("%20", " ");
        if (Files.exists(Path.of(absolutePath))) {
            dataSource.setUrl(String.format("jdbc:sqlite:%s", absolutePath));
        } else if (Files.exists(Path.of(relativePath))) {
            dataSource.setUrl(String.format("jdbc:sqlite:%s", relativePath));
        } else {
            throw new ExceptionInInitializerError("File for db routes not found");
        }

        return dataSource;
    }
}
