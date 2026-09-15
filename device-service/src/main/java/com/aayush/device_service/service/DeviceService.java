package com.aayush.device_service.service;

import com.aayush.device_service.exception.DeviceNotFoundException;
import com.aayush.device_service.dto.DeviceDto;
import com.aayush.device_service.entity.Device;
import com.aayush.device_service.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceDto getDeviceById(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Device Not Found with id : " + id));

        return mapToDto(device);
    }


    public DeviceDto createDevice(DeviceDto deviceDto) {
        // actual entity it stored in db, not the dto, so we need to covner it

        Device createdDevice = new Device();

        createdDevice.setName(deviceDto.getName());
        createdDevice.setType(deviceDto.getType());
        createdDevice.setLocation(deviceDto.getLocation());
        createdDevice.setUserId(deviceDto.getUserId());

        Device savedDevice = deviceRepository.save(createdDevice);


        /*
            same reason of sending back a  dto
            real world systems would have a response and request dto
            i think this exposes the id as well in the return dto, will have to check
         */
        return mapToDto(savedDevice);

    }


    public DeviceDto updateDevice(Long id, DeviceDto deviceDto) {

        Device deviceToUpdate = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Device Not Found with id : " + id));

        deviceToUpdate.setName(deviceToUpdate.getName());
        deviceToUpdate.setLocation(deviceToUpdate.getLocation());
        deviceToUpdate.setType(deviceDto.getType());
        deviceToUpdate.setUserId(deviceDto.getUserId());

        Device savedDevice = deviceRepository.save(deviceToUpdate);

        return mapToDto(savedDevice);
    }

    public void deleteDevice(Long id) {
        if(!deviceRepository.existsById(id)) {
            throw new DeviceNotFoundException("Device Not Found with id : " + id);
        }
        deviceRepository.deleteById(id);
    }

    private DeviceDto mapToDto(Device device) {
        return DeviceDto.builder()
                .id(device.getId())
                .name(device.getName())
                .type(device.getType())
                .location(device.getLocation())
                .userId(device.getUserId())
                .build();
    }

}
