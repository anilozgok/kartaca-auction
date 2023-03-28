package com.anilcan.kartaca.exception.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
    USER_NOT_FOUND("User Not Found.", "user.not.found", HttpStatus.NOT_FOUND),
    USER_ALREADY_REGISTERED("User Already Registered.", "user.already.registered", HttpStatus.BAD_REQUEST),
    UNKNOWN_EXCEPTION("Unknown Error Occurred", "unknown.exception", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;

    private final String errorName;

    private final HttpStatus errorCode;


}