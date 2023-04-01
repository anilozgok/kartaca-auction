package com.anilcan.kartaca.exception;

import com.anilcan.kartaca.exception.base.BaseException;
import com.anilcan.kartaca.exception.messages.ExceptionMessage;

public class ItemNotFoundException extends BaseException {

    public ItemNotFoundException() {
        super(ExceptionMessage.ITEM_NOT_FOUND);
    }

}
