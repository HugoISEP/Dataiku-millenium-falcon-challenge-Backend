package com.starwars.millenniumfalcononboardcomputer.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "ROUTES")
@Data
@IdClass(RouteId.class)
public class Route {
    @Id
    @Column(nullable = false)
    private String origin;
    @Id
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    @Min(value = 1)
    private int travelTime;
}
