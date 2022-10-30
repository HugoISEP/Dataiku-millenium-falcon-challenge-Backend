package com.starwars.millenniumfalcononboardcomputer.service;

import com.starwars.millenniumfalcononboardcomputer.configuration.MillenniumFalconConfiguration;
import com.starwars.millenniumfalcononboardcomputer.model.pojo.AccessibleRoute;
import com.starwars.millenniumfalcononboardcomputer.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final MillenniumFalconConfiguration millenniumFalconConfiguration;

    /**
     * Get a Map of the planets accessible from each planet
     * @return Map of accessible routes
     */
    public Map<String, Set<AccessibleRoute>> getAccessibleRoutesMap() {
        val routes = routeRepository.findAll();
        val routesAccessible = new HashMap<String, Set<AccessibleRoute>>();
        routes
                .stream()
                .filter(route -> route.getTravelTime() <= millenniumFalconConfiguration.getAutonomy())
                .forEach(route -> {
                    fillAccessibleRoutes(route.getOrigin(), route.getDestination(), route.getTravelTime(), routesAccessible);
                    fillAccessibleRoutes(route.getDestination(), route.getOrigin(), route.getTravelTime(), routesAccessible);
        });
        return routesAccessible;
    }

    /**
     * Fill the accessible routes map
     * @param origin origin planet
     * @param destination destination planet
     * @param autonomy Autonomy needed to reach the destination from origin
     * @param accessibleRoutes current map of the accessible routes
     */
    private void fillAccessibleRoutes(String origin, String destination, int autonomy, Map<String, Set<AccessibleRoute>> accessibleRoutes) {
        val accessibleRoute = new AccessibleRoute(destination, autonomy);
        if (!accessibleRoutes.containsKey(origin)) {
            accessibleRoutes.put(origin, new HashSet<>(Set.of(accessibleRoute)));
        } else {
            accessibleRoutes.get(origin).add(accessibleRoute);
        }
    }
}
