package com.anilcan.kartaca.model.response;

import com.anilcan.kartaca.exception.messages.ExceptionMessage;

import java.time.LocalDateTime;

public record ErrorResponse(ExceptionMessage exceptionMessage, LocalDateTime occurredAt) {
}
