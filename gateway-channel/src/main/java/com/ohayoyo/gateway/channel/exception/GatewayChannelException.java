package com.ohayoyo.gateway.channel.exception;

import java.util.Locale;

public class GatewayChannelException extends Exception {

    public GatewayChannelException() {
    }

    public GatewayChannelException(String message) {
        super(message);
    }

    public GatewayChannelException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatewayChannelException(Throwable cause) {
        super(cause);
    }

    public static void exception(String exMsg, Object... args) throws GatewayChannelException {
        throw new GatewayChannelException(String.format(Locale.CHINA, exMsg, args));
    }

}
