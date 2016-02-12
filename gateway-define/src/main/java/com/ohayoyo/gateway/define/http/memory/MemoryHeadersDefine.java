package com.ohayoyo.gateway.define.http.memory;

import com.ohayoyo.gateway.define.http.HeadersDefine;
import com.ohayoyo.gateway.define.AbstractParametersDefine;

public class MemoryHeadersDefine extends AbstractParametersDefine implements HeadersDefine {

    public MemoryHeadersDefine() {
        super(PARAMETER_NAME_HEADERS);
    }

}
