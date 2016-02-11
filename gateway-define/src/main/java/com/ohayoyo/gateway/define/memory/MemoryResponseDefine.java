package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.http.EntityDefine;
import com.ohayoyo.gateway.define.http.HeadersDefine;
import com.ohayoyo.gateway.define.http.ResponseDefine;
import com.ohayoyo.gateway.define.http.StatusDefine;

import java.util.Set;

public class MemoryResponseDefine implements ResponseDefine {

    private Set<StatusDefine> statuses;

    private HeadersDefine headers;

    private EntityDefine entity;

    public Set<StatusDefine> getStatuses() {
        return statuses;
    }

    public MemoryResponseDefine setStatuses(Set<StatusDefine> statuses) {
        this.statuses = statuses;
        return this;
    }

    public HeadersDefine getHeaders() {
        return headers;
    }

    public MemoryResponseDefine setHeaders(HeadersDefine headers) {
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
