package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.StatusDefine;

public class MemoryStatusDefine implements StatusDefine {

    private String statusCode;

    private String reasonPhrase;

    private String errorSolution;

    public String getStatusCode() {
        return statusCode;
    }

    public MemoryStatusDefine setStatusCode(String statusCode) {
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

    public String getErrorSolution() {
        return errorSolution;
    }

    public MemoryStatusDefine setErrorSolution(String errorSolution) {
        this.errorSolution = errorSolution;
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
