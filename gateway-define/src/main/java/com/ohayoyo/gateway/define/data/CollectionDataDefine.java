package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.TypeDefine;

import java.util.Collection;

public class CollectionDataDefine extends AbstractDataDefine {

    private Collection<DataDefine> collection;

    public CollectionDataDefine() {
        super(TypeDefine.COLLECTION);
    }

    public Collection<DataDefine> getCollection() {
        return collection;
    }

    public CollectionDataDefine setCollection(Collection<DataDefine> collection) {
        this.collection = collection;
        return this;
    }

}
