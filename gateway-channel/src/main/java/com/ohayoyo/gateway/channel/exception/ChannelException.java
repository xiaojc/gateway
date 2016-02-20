package com.ohayoyo.gateway.channel.exception;

import java.util.Locale;

public class ChannelException extends Exception {

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

    public static void exception(String exMsg, Object... args) throws ChannelException {
        throw new ChannelException(String.format(Locale.CHINA, exMsg, args));
    }

}
