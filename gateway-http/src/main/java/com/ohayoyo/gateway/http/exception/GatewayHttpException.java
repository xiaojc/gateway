package com.ohayoyo.gateway.http.exception;

import java.util.Locale;

/**
 * @author 蓝明乐
 */
public class GatewayHttpException extends RuntimeException {

    public GatewayHttpException() {
    }

    public GatewayHttpException(String message) {
        super(message);
    }

    public GatewayHttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatewayHttpException(Throwable cause) {
        super(cause);
    }

    public static void exception(String exMsg, Object... args) throws GatewayHttpException {
        throw new GatewayHttpException(String.format(Locale.CHINA, exMsg, args));
    }

    public static void exception(Throwable cause) throws GatewayHttpException {
        throw new GatewayHttpException(cause);
    }

}
