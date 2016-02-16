package com.ohayoyo.gateway.define.http;

import com.ohayoyo.gateway.define.ObjectDefine;
import com.ohayoyo.gateway.define.DataDefine;

/**
 * @author 蓝明乐
 */
public interface EntityDefine extends ObjectDefine {

    String getContentType();

    EntityDefine setContentType(String contentType);

    DataDefine getEntityData();

    EntityDefine setEntityData(DataDefine entityData);

}
