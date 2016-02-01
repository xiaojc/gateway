package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.core.EntityDefine;
import com.ohayoyo.gateway.define.core.HeaderDefine;
import com.ohayoyo.gateway.define.core.ResponseDefine;
import com.ohayoyo.gateway.define.core.StatusDefine;

import java.util.Set;

public class MemoryResponseDefine implements ResponseDefine {

    private Set<StatusDefine> statuses;

    private Set<HeaderDefine> headers;

    private EntityDefine entity;

    public Set<StatusDefine> getStatuses() {
        return statuses;
    }

    public MemoryResponseDefine setStatuses(Set<StatusDefine> statuses) {
        this.statuses = statuses;
        return this;
    }

    public Set<HeaderDefine> getHeaders() {
        return headers;
    }

    public MemoryResponseDefine setHeaders(Set<HeaderDefine> headers) {
        this.headers = headers;
        return this;
    }

    public EntityDefine getEntity() {
        return entity;
    }

    public MemoryResponseDefine setEntity(EntityDefine entity) {
        this.entity = entity;
        return this;
    }

}
