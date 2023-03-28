package com.anilcan.kartaca.exception.base;

import com.anilcan.kartaca.exception.messages.ExceptionMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseException extends RuntimeException{
    protected final ExceptionMessage exceptionMessage;
}
