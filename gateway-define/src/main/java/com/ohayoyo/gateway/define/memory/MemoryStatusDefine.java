package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.core.StatusDefine;

public class MemoryStatusDefine implements StatusDefine {

    private String type;

    private String statusCode;

    private String reasonPhrase;

    private String errorSolution;

    public MemoryStatusDefine() {
    }

    public String getType() {
        return type;
    }

    public MemoryStatusDefine setType(String type) {
        this.type = type;
        return this;
    }

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
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return statusCode != null ? statusCode.equals(that.statusCode) : that.statusCode == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (statusCode != null ? statusCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(statusCode).append(" ").append(reasonPhrase).toString();
    }

}
