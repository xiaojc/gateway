package com.ohayoyo.gateway.define.core;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public abstract class AbstractParametersDefine implements ParametersDefine {

    private Set<Parameter> parameters;

    @Override
    public Set<Parameter> getParameters() {
        return parameters;
    }

    @Override
    public AbstractParametersDefine setParameters(Set<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractParametersDefine)) return false;
        AbstractParametersDefine that = (AbstractParametersDefine) o;
        return parameters != null ? parameters.equals(that.parameters) : that.parameters == null;
    }

    @Override
    public int hashCode() {
        return parameters != null ? parameters.hashCode() : 0;
    }

}
