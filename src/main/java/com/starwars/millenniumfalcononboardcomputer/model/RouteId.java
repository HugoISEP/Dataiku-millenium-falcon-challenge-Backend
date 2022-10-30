package com.starwars.millenniumfalcononboardcomputer.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RouteId implements Serializable {
    private String origin;
    private String destination;
}
