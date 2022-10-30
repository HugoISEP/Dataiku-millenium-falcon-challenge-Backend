package com.starwars.millenniumfalcononboardcomputer.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MillenniumFalconConfigurationTest {
    @Autowired
    private MillenniumFalconConfiguration millenniumFalconConfiguration;

    @Test
    void millenniumFalconConfigurationTest() {
        // Given a file located in classpath:millennium-falcon.json with all the configuration properties
        // When the application context is loaded
        // Then we should get the all the properties defined in that file
        assertEquals(6, millenniumFalconConfiguration.getAutonomy());
        assertEquals("Tatooine", millenniumFalconConfiguration.getDeparture());
        assertEquals("Endor", millenniumFalconConfiguration.getArrival());
        assertEquals("universe.db", millenniumFalconConfiguration.getRoutesDb());
    }

}
