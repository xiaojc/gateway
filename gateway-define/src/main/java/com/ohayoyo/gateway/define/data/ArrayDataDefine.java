package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.TypeDefine;

public class ArrayDataDefine extends AbstractDataDefine {

    private DataDefine[] arrays;

    public ArrayDataDefine() {
        super(TypeDefine.ARRAYS);
    }

    public DataDefine[] getArrays() {
        return arrays;
    }

    public ArrayDataDefine setArrays(DataDefine[] arrays) {
        this.arrays = arrays;
        return this;
    }

}
