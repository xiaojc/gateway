package com.ohayoyo.gateway.session;

import java.util.Locale;

/**
 * @author 蓝明乐
 */
public class VerifySessionException extends GatewaySessionException {

    public VerifySessionException() {
    }

    public VerifySessionException(String message) {
        super(message);
    }

    public VerifySessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerifySessionException(Throwable cause) {
        super(cause);
    }

    public static void exception(String exMsg, Object... args) throws VerifySessionException {
        throw new VerifySessionException(String.format(Locale.CHINA, exMsg, args));
    }

}
