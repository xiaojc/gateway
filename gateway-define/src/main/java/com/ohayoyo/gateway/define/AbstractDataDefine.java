package com.ohayoyo.gateway.define;

import java.util.Arrays;
import java.util.Collection;

public abstract class AbstractDataDefine implements DataDefine {

    private String defineType;

    private Collection<DataScope> dataScopes;

    public AbstractDataDefine(String defineType, DataScope... dataScopes) {
        this(defineType, Arrays.asList(dataScopes));
    }

    public AbstractDataDefine(String defineType, Collection<DataScope> dataScopes) {
        this.defineType = defineType;
        this.dataScopes = dataScopes;
    }

    @Override
    public String getDefineType() {
        return defineType;
    }

    @Override
    public Collection<DataScope> getDataScopes() {
        return dataScopes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractDataDefine)) return false;
        AbstractDataDefine that = (AbstractDataDefine) o;
        return defineType != null ? defineType.equals(that.defineType) : that.defineType == null;
    }

    @Override
    public int hashCode() {
        return defineType != null ? defineType.hashCode() : 0;
    }
}
