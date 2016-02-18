package com.ohayoyo.gateway.client.exception;

import java.util.Locale;

/**
 * @author 蓝明乐
 */
public class ValidatorException extends GatewayException {

    public ValidatorException() {
    }

    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }

    public static void exception(String exMsg, Object... args) throws ValidatorException {
        throw new ValidatorException(String.format(Locale.CHINA, exMsg, args));
    }

}
