package com.starwars.millenniumfalcononboardcomputer.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "ROUTES")
@Data
@Accessors(chain = true)
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