package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.AbstractDataDefine;
import com.ohayoyo.gateway.define.DataDefine;

/**
 * @author 蓝明乐
 */
public abstract class AbstractCollectionDataDefine extends AbstractDataDefine {

    private DataDefine parameterType;

    protected AbstractCollectionDataDefine(String name) {
        super(name);
    }

    public DataDefine getParameterType() {
        return parameterType;
    }

    public AbstractCollectionDataDefine setParameterType(DataDefine parameterType) {
        this.parameterType = parameterType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractCollectionDataDefine)) return false;
        if (!super.equals(o)) return false;
        AbstractCollectionDataDefine that = (AbstractCollectionDataDefine) o;
        return parameterType != null ? parameterType.equals(that.parameterType) : that.parameterType == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (parameterType != null ? parameterType.hashCode() : 0);
        return result;
    }

}
