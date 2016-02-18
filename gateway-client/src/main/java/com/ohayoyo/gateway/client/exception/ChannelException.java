package com.ohayoyo.gateway.client.exception;

import java.util.Locale;

/**
 * @author 蓝明乐
 */
public class ChannelException extends GatewayException {

    public ChannelException() {
    }

    public ChannelException(String message) {
        super(message);
    }

    public ChannelException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChannelException(Throwable cause) {
        super(cause);
    }

    public ChannelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public static void exception(String exMsg, Object... args) throws ChannelException {
        throw new ChannelException(String.format(Locale.CHINA, exMsg, args));
    }

}
