package com.ohayoyo.gateway.container;

public interface GatewayAction<Parameter> {

    String getName();

    GatewayAction<Parameter> setName(String name);

    Parameter getParameter();

    GatewayAction<Parameter> setParameter(Parameter parameter);

}
