package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.AbstractDataDefine;
import com.ohayoyo.gateway.define.DataDefine;

import java.util.Collection;

public abstract class AbstractCollectionDataDefine<Define extends Collection<DataDefine>> extends AbstractDataDefine {

    private Define collection;

    protected AbstractCollectionDataDefine(String name) {
        super(name);
    }

    public Define getCollection() {
        return collection;
    }

    public AbstractCollectionDataDefine setCollection(Define collection) {
        this.collection = collection;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractCollectionDataDefine)) return false;
        if (!super.equals(o)) return false;
        AbstractCollectionDataDefine<?> that = (AbstractCollectionDataDefine<?>) o;
        return collection != null ? collection.equals(that.collection) : that.collection == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (collection != null ? collection.hashCode() : 0);
        return result;
    }

}
