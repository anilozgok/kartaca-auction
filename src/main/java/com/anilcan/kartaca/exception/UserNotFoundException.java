package com.anilcan.kartaca.exception;

import com.anilcan.kartaca.exception.base.BaseException;
import com.anilcan.kartaca.exception.messages.ExceptionMessage;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException(){
        super(ExceptionMessage.USER_NOT_FOUND);
    }
}
