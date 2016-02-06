package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.ReferenceDefine;

import java.util.Map;

public class MemoryReferenceDefine implements ReferenceDefine {

    private String reference;

    private Map<String, Object> mapping;

    @Override
    public String getReference() {
        return reference;
    }

    @Override
    public MemoryReferenceDefine setReference(String reference) {
        this.reference = reference;
        return this;
    }

    @Override
    public Map<String, Object> getMapping() {
        return mapping;
    }

    @Override
    public MemoryReferenceDefine setMapping(Map<String, Object> mapping) {
        this.mapping = mapping;
        return this;
    }

}
