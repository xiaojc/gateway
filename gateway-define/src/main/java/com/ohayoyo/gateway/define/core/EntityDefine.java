package com.ohayoyo.gateway.define.core;

import java.io.Serializable;

/**
 * 实体定义
 */
public interface EntityDefine extends Serializable {

    String getType();

    EntityDefine setType(String type);

    Object getData();

    EntityDefine setData(Object data);

}
