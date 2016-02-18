package com.ohayoyo.gateway.http.exception;

import java.util.Locale;

/**
 * @author 蓝明乐
 */
public class HttpGatewayException extends Exception {

    public HttpGatewayException() {
    }

    public HttpGatewayException(String message) {
        super(message);
    }

    public HttpGatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpGatewayException(Throwable cause) {
        super(cause);
    }

    public static void exception(String exMsg, Object... args) throws HttpGatewayException {
        throw new HttpGatewayException(String.format(Locale.CHINA, exMsg, args));
    }

    public static void exception(Throwable cause) throws HttpGatewayException {
        throw new HttpGatewayException(cause);
    }

}
