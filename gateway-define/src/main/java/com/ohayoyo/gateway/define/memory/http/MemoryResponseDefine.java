package com.ohayoyo.gateway.define.memory.http;

import com.ohayoyo.gateway.define.core.EntityDefine;
import com.ohayoyo.gateway.define.core.HeadersDefine;
import com.ohayoyo.gateway.define.core.ResponseDefine;
import com.ohayoyo.gateway.define.core.StatusDefine;

import java.util.Set;

/**
 * @author 蓝明乐
 */
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
