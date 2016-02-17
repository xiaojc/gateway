package com.ohayoyo.gateway.define.core;

import com.ohayoyo.gateway.define.ObjectDefine;

/**
 * @author 蓝明乐
 */
public interface EntityDefine extends ObjectDefine {

    String getContentType();

    EntityDefine setContentType(String contentType);

    Object getEntityData();

    EntityDefine setEntityData(Object entityData);

}
