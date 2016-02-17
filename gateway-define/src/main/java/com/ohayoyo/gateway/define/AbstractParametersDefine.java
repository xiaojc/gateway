package com.ohayoyo.gateway.define;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public abstract class AbstractParametersDefine implements ParametersDefine {

    private Set<ParameterDefine> fields;


    @Override
    public Set<ParameterDefine> getFields() {
        return fields;
    }

    @Override
    public AbstractParametersDefine setFields(Set<ParameterDefine> fields) {
        this.fields = fields;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractParametersDefine)) return false;
        AbstractParametersDefine that = (AbstractParametersDefine) o;
        return fields != null ? fields.equals(that.fields) : that.fields == null;
    }

    @Override
    public int hashCode() {
        return fields != null ? fields.hashCode() : 0;
    }

}
