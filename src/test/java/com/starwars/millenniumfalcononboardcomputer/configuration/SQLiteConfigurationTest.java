package com.starwars.millenniumfalcononboardcomputer.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SQLiteConfigurationTest {
    @Autowired
    private DriverManagerDataSource driverManagerDataSource;

    @Test
    void databaseConfigurationTest() {
        // Given a db file with a path either absolute or relative to the location of the millennium-falcon.json
        // When the application context is loaded
        // Then the database should be configured
        assertEquals("admin", driverManagerDataSource.getUsername());
        assertEquals("admin", driverManagerDataSource.getPassword());
        assertEquals("jdbc:sqlite:src/main/resources/universe.db", driverManagerDataSource.getUrl());
    }
}
