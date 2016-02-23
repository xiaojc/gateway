package com.ohayoyo.gateway.memory;

import com.ohayoyo.gateway.define.GatewayStatus;

public class MemoryGatewayStatus implements GatewayStatus {

    private Integer code;

    private String phrase;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getPhrase() {
        return phrase;
    }

    @Override
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryGatewayStatus)) return false;
        MemoryGatewayStatus that = (MemoryGatewayStatus) o;
        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

}
