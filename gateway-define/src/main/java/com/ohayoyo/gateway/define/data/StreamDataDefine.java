package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.AbstractDataDefine;
import com.ohayoyo.gateway.define.DataDefine;
import com.ohayoyo.gateway.define.DataTypeDefine;

/**
 * @author 蓝明乐
 */
public class StreamDataDefine extends AbstractDataDefine implements DataDefine {

    public StreamDataDefine() {
        super(DataTypeDefine.DATA_TYPE_STREAM);
    }

}
