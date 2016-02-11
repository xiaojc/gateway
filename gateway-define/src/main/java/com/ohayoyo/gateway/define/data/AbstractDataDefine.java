package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.TypeDefine;

abstract class AbstractDataDefine implements DataDefine {

    private TypeDefine type;

    protected AbstractDataDefine(TypeDefine type) {
        this.type = type;
    }

    @Override
    public TypeDefine getType() {
        return type;
    }

    @Override
    public AbstractDataDefine setType(TypeDefine type) {
        this.type = type;
        return this;
    }

}
