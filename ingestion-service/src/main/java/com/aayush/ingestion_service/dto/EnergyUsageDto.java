package com.aayush.ingestion_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.Instant;

@Builder
public record EnergyUsageDto (

    Long deviceId,

    double energyConsumed,

    // What is instant?
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Instant timestamp
) {}


/*
we should use Java records for immutable DTOs.
DTOs good candidate for DTOs as they are just data being passed from cliente to server
we need to model data for input and output
Entities cannot be records
 */
