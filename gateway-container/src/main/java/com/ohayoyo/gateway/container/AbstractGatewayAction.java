package com.ohayoyo.gateway.container;

public abstract class AbstractGatewayAction<Parameter> implements GatewayAction<Parameter> {

    private String name;

    private Parameter parameter;

    public AbstractGatewayAction(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GatewayAction<Parameter> setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Parameter getParameter() {
        return parameter;
    }

    @Override
    public GatewayAction<Parameter> setParameter(Parameter parameter) {
        this.parameter = parameter;
        return this;
    }

}
