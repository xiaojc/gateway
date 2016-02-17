package com.ohayoyo.gateway.define.memory.http;

import com.ohayoyo.gateway.define.core.StatusDefine;

/**
 * @author 蓝明乐
 */
public class MemoryStatusDefine implements StatusDefine {

    private Integer statusCode;

    private String reasonPhrase;

    public Integer getStatusCode() {
        return statusCode;
    }

    public MemoryStatusDefine setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public MemoryStatusDefine setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryStatusDefine)) return false;
        MemoryStatusDefine that = (MemoryStatusDefine) o;
        return statusCode != null ? statusCode.equals(that.statusCode) : that.statusCode == null;
    }

    @Override
    public int hashCode() {
        return statusCode != null ? statusCode.hashCode() : 0;
    }

}
