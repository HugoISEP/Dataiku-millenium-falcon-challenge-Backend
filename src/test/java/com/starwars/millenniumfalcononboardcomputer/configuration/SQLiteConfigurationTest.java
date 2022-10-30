package com.starwars.millenniumfalcononboardcomputer.configuration;

import com.starwars.millenniumfalcononboardcomputer.model.entity.Route;
import com.starwars.millenniumfalcononboardcomputer.repository.RouteRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SQLiteConfigurationTest {
    @Autowired
    private DriverManagerDataSource driverManagerDataSource;
    @Autowired
    private RouteRepository routeRepository;

    @Test
    void databaseConfigurationTest() {
        // Given a db file with a path either absolute or relative to the location of the millennium-falcon.json
        // When the application context is loaded
        // Then the database should be configured
        assertEquals("admin", driverManagerDataSource.getUsername());
        assertEquals("admin", driverManagerDataSource.getPassword());
        assertEquals("jdbc:sqlite:src/main/resources/universe.db", driverManagerDataSource.getUrl());
    }

    @Test
    void allRoutesLoadedTest() {
        // Given all routes defined in the universe.db file
        val route1 = new Route()
                .setOrigin("Tatooine")
                .setDestination("Dagobah")
                .setTravelTime(6);
        val route2 = new Route()
                .setOrigin("Dagobah")
                .setDestination("Endor")
                .setTravelTime(4);
        val route3 = new Route()
                .setOrigin("Dagobah")
                .setDestination("Hoth")
                .setTravelTime(1);
        val route4 = new Route()
                .setOrigin("Hoth")
                .setDestination("Endor")
                .setTravelTime(1);
        val route5 = new Route()
                .setOrigin("Tatooine")
                .setDestination("Hoth")
                .setTravelTime(6);

        // When all routes from the database are retrieved
        val allRoutes = routeRepository.findAll();

        // Then all routes are present
        assertEquals(5, allRoutes.size());
        assertTrue(allRoutes.contains(route1));
        assertTrue(allRoutes.contains(route2));
        assertTrue(allRoutes.contains(route3));
        assertTrue(allRoutes.contains(route4));
        assertTrue(allRoutes.contains(route5));
    }
}
