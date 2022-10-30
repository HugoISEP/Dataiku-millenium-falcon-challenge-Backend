package com.starwars.millenniumfalcononboardcomputer.configuration.sqlite;

import com.starwars.millenniumfalcononboardcomputer.configuration.MillenniumFalconConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SQLiteConfiguration {
    private final MillenniumFalconConfiguration millenniumFalconConfiguration;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;
    private final String PATH_TO_RESOURCES_FOLDER = "src/main/resources";

    @Bean
    public DataSource dataSource() {
        val dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(String.format("jdbc:sqlite:%s/%s", PATH_TO_RESOURCES_FOLDER, millenniumFalconConfiguration.getRoutesDb()));
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}
