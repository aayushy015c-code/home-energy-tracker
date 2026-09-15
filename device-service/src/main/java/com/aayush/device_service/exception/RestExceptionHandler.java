package com.aayush.device_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
Purpose : Whenvere a controlelr throws an exception, check if this class knows how handle it
 */
public class RestExceptionHandler {

    // exception when a device with a speific id is not found
    // no need to handle/change anything in controller class
    @ExceptionHandler(DeviceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(DeviceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
