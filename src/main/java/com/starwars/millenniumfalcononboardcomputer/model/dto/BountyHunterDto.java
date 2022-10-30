package com.starwars.millenniumfalcononboardcomputer.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record BountyHunterDto(@NotNull String planet, @Min(0) int day) {}
