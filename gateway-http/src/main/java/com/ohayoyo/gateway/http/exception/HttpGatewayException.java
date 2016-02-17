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

    public HttpGatewayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public static void exception(String exMsg, Object... args) throws HttpGatewayException {
        throw new HttpGatewayException(String.format(Locale.CHINA, exMsg, args));
    }

}
