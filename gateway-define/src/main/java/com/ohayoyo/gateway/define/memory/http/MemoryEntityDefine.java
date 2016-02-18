package com.ohayoyo.gateway.define.memory.http;

import com.ohayoyo.gateway.define.http.EntityDefine;

/**
 * @author 蓝明乐
 */
public class MemoryEntityDefine implements EntityDefine {

    private String contentType;

    private Object entityData;

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public MemoryEntityDefine setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    @Override
    public Object getEntityData() {
        return entityData;
    }

    @Override
    public MemoryEntityDefine setEntityData(Object entityData) {
        this.entityData = entityData;
        return this;
    }

}
