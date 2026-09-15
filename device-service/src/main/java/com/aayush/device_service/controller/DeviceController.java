package com.aayush.device_service.controller;

import com.aayush.device_service.dto.DeviceDto;
import com.aayush.device_service.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/device")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getDeviceById(@PathVariable Long id) {
        DeviceDto device = deviceService.getDeviceById(id);

        return ResponseEntity.ok(device);
    }

    @PostMapping("/create")
    public ResponseEntity<DeviceDto> createdDevice(@RequestBody DeviceDto deviceDto) {
        DeviceDto createdDto = deviceService.createDevice(deviceDto);

        return ResponseEntity.ok(createdDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<DeviceDto> updateDevice (
            @PathVariable Long id,
            @RequestBody DeviceDto deviceDto
    ) {
        DeviceDto updatedDevice = deviceService.updateDevice(id, deviceDto);

        return ResponseEntity.ok(updatedDevice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);

        return ResponseEntity.noContent().build();
    }
}
