package com.ohayoyo.gateway.define.http.memory;

import com.ohayoyo.gateway.define.http.EntityDefine;
import com.ohayoyo.gateway.define.DataDefine;

public class MemoryEntityDefine implements EntityDefine {

    private String contentType;

    private DataDefine entityData;

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
    public DataDefine getEntityData() {
        return entityData;
    }

    @Override
    public MemoryEntityDefine setEntityData(DataDefine entityData) {
        this.entityData = entityData;
        return this;
    }

}
