package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.DataDefine;
import com.ohayoyo.gateway.define.DataTypeDefine;

import java.util.List;

public class ListDataDefine extends AbstractCollectionDataDefine<List<DataDefine>> {

    public ListDataDefine() {
        super(DataTypeDefine.DATA_TYPE_LIST);
    }

}
