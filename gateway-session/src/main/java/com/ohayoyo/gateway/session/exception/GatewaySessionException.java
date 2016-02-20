package com.ohayoyo.gateway.session.exception;

/**
 * @author 蓝明乐
 */
public class GatewaySessionException extends Exception {
    public GatewaySessionException() {
    }

    public GatewaySessionException(String message) {
        super(message);
    }

    public GatewaySessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatewaySessionException(Throwable cause) {
        super(cause);
    }

}
