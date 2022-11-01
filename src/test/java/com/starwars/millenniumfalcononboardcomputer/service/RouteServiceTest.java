package com.starwars.millenniumfalcononboardcomputer.service;

import com.starwars.millenniumfalcononboardcomputer.configuration.MillenniumFalconConfiguration;
import com.starwars.millenniumfalcononboardcomputer.model.entity.Route;
import com.starwars.millenniumfalcononboardcomputer.model.pojo.AccessibleRoute;
import com.starwars.millenniumfalcononboardcomputer.repository.RouteRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RouteServiceTest {
    @Mock
    private RouteRepository routeRepository;
    @InjectMocks
    @Spy
    private MillenniumFalconConfiguration millenniumFalconConfiguration;
    private RouteService routeService;

    @BeforeEach
    void setUp() {
        routeService = Mockito.spy(new RouteService(routeRepository, millenniumFalconConfiguration));
    }

    @Test
    void getAccessibleRoutesMapTest() {
        // Given 3 routes;
        val route1 = new Route()
                .setOrigin("Tatooine")
                .setDestination("Dagobah")
                .setTravelTime(6);
        val route2 = new Route()
                .setOrigin("Dagobah")
                .setDestination("Endor")
                .setTravelTime(4);
        val route3 = new Route()
                .setOrigin("Hoth")
                .setDestination("Endor")
                .setTravelTime(1);
        val routes = new ArrayList<>(List.of(route1, route2, route3));
        Mockito.when(routeRepository.findAll()).thenReturn(routes);
        Mockito.when(millenniumFalconConfiguration.getAutonomy()).thenReturn(Integer.MAX_VALUE);

        // When we get the accessible routes map
        val result = routeService.getAccessibleRoutesMap();

        // Then we should get the for each planet, a list of planet accessible
        assertTrue(result.get("Tatooine").contains(new AccessibleRoute("Dagobah", 6)));
        assertTrue(result.get("Dagobah").containsAll(Set.of(new AccessibleRoute("Tatooine", 6), new AccessibleRoute("Endor", 4))));
        assertTrue(result.get("Endor").containsAll(Set.of(new AccessibleRoute("Dagobah", 4), new AccessibleRoute("Hoth", 1))));
        assertTrue(result.get("Hoth").contains(new AccessibleRoute("Endor", 1)));
    }
}
