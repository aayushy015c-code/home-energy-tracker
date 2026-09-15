package com.aayush.device_service.entity;

import com.aayush.device_service.model.DeviceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sound.midi.MidiDeviceTransmitter;

@Entity
@Table(name = "device")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING) // for deserialisation from db (what does this mean?)
    private DeviceType type;

    private String location;

    private Long userId;

}
