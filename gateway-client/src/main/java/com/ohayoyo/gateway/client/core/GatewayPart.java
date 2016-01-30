package com.ohayoyo.gateway.client.core;

public interface GatewayPart<T> extends GatewayScope {

    T getPart() throws GatewayException;

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
