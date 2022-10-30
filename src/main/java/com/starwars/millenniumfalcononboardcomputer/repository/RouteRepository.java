package com.starwars.millenniumfalcononboardcomputer.repository;

import com.starwars.millenniumfalcononboardcomputer.model.Route;
import com.starwars.millenniumfalcononboardcomputer.model.RouteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, RouteId> {}
