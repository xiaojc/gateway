package com.ohayoyo.gateway.define.http.memory;

import com.ohayoyo.gateway.define.AbstractParametersDefine;
import com.ohayoyo.gateway.define.http.QueriesDefine;

/**
 * @author 蓝明乐
 */
public class MemoryQueriesDefine extends AbstractParametersDefine implements QueriesDefine {

    public MemoryQueriesDefine() {
        super(PARAMETER_NAME_QUERIES);
    }

}
