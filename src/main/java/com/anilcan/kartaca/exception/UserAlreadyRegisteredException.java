package com.anilcan.kartaca.exception;

import com.anilcan.kartaca.exception.base.BaseException;
import com.anilcan.kartaca.exception.messages.ExceptionMessage;

public class UserAlreadyRegisteredException extends BaseException {

    public UserAlreadyRegisteredException() {

        super(ExceptionMessage.USER_ALREADY_REGISTERED);
    }

}
