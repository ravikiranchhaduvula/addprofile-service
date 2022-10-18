package com.skilltracker.app.addprofile.service.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddProfileException extends Exception{
    public AddProfileException(String message) {
      log.error(message);
    }
}
