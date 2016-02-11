package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.ObjectDefine;
import com.ohayoyo.gateway.define.TypeDefine;

public interface DataDefine extends ObjectDefine {

    TypeDefine getType();

    DataDefine setType(TypeDefine type);

}
