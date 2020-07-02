package com.people10.springboot.rest.challenge.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad Password or User already exists")
public class BadRequestException extends RuntimeException {
}
