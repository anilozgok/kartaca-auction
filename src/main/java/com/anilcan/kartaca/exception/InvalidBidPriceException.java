package com.anilcan.kartaca.exception;

import com.anilcan.kartaca.exception.base.BaseException;
import com.anilcan.kartaca.exception.messages.ExceptionMessage;

public class InvalidBidPriceException extends BaseException {

    public InvalidBidPriceException() {
        super(ExceptionMessage.INVALID_BID_PRICE_EXCEPTION);
    }

}
