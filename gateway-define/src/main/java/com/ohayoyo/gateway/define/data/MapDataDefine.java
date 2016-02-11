package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.TypeDefine;

public class MapDataDefine extends AbstractDataDefine {

    private DataDefine key;

    private DataDefine value;

    public MapDataDefine() {
        super(TypeDefine.MAP);
    }

    public DataDefine getKey() {
        return key;
    }

    public MapDataDefine setKey(DataDefine key) {
        this.key = key;
        return this;
    }

    public DataDefine getValue() {
        return value;
    }

    public MapDataDefine setValue(DataDefine value) {
        this.value = value;
        return this;
    }

}
