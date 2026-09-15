package com.aayush.ingestion_service.controller;

import com.aayush.ingestion_service.dto.EnergyUsageDto;
import com.aayush.ingestion_service.service.IngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ingestion")
@RequiredArgsConstructor
public class IngestionController {

    /*
    Only Post ENDPOINT for devices to send data.
     */

    private final IngestionService ingestionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void ingestData(@RequestBody EnergyUsageDto usageDto) {
        ingestionService.ingestEnergyUsage(usageDto);
    }
}
