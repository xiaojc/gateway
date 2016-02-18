package com.ohayoyo.gateway.define.http;

import com.ohayoyo.gateway.define.core.ObjectDefine;

/**
 * @author 蓝明乐
 */
public interface EntityDefine extends ObjectDefine {

    String getContentType();

    EntityDefine setContentType(String contentType);

    Object getEntityData();

    EntityDefine setEntityData(Object entityData);

}
