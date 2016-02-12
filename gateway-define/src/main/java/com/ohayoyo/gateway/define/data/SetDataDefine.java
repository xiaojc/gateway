package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.DataDefine;
import com.ohayoyo.gateway.define.DataTypeDefine;

import java.util.Set;

public class SetDataDefine extends AbstractCollectionDataDefine<Set<DataDefine>> {

    public SetDataDefine() {
        super(DataTypeDefine.DATA_TYPE_LIST);
    }

}
