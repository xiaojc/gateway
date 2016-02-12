package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.AbstractDataDefine;
import com.ohayoyo.gateway.define.DataDefine;
import com.ohayoyo.gateway.define.DataTypeDefine;

public class ArrayDataDefine extends AbstractDataDefine {

    private DataDefine[] arrays;

    public ArrayDataDefine() {
        super(DataTypeDefine.DATA_TYPE_ARRAYS);
    }

    public DataDefine[] getArrays() {
        return arrays;
    }

    public ArrayDataDefine setArrays(DataDefine[] arrays) {
        this.arrays = arrays;
        return this;
    }

}
