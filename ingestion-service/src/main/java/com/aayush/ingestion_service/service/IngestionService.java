package com.aayush.ingestion_service.service;

import com.aayush.ingestion_service.dto.EnergyUsageDto;
import com.aayush.kafka.event.EnergyUsageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class IngestionService {

    // type of key, event we want to put on topic
    private final KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate;
    // we want this event to be a class to be serialised by jackson and then be put on the kafka topic in json format.
    // putting it on top level package
    // other strategies exist, common jar w shared classes

    // actual mnethod which will be publishing kafka events to the topic, aka producer
    public void ingestEnergyUsage(EnergyUsageDto input) {
        //converting DTO to Event
        EnergyUsageEvent event = EnergyUsageEvent.builder()
                .deviceId(input.deviceId())
                .energyConsumed(input.energyConsumed())
                .timestamp(input.timestamp())
                .build();

        // Send Event to Kakfa topic
        // why no key? key is reqiured when ordering of events is required, not over here.
        kafkaTemplate.send("energy-usage", event);
        log.info("Ingested Energy Usage Event: {}:", event);
    }
}
