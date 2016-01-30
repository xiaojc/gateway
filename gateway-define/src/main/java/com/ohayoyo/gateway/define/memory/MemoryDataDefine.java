package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.core.DataDefine;

public class MemoryDataDefine implements DataDefine {

    private String name;

    private String type;

    private Boolean required;

    private Integer length;

    private Object defaultValue;

    private String description;

    private Object reference;

    public MemoryDataDefine() {
    }

    public String getName() {
        return name;
    }

    public MemoryDataDefine setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public MemoryDataDefine setType(String type) {
        this.type = type;
        return this;
    }

    public Boolean getRequired() {
        return required;
    }

    public MemoryDataDefine setRequired(Boolean required) {
        this.required = required;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public MemoryDataDefine setLength(Integer length) {
        this.length = length;
        return this;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public MemoryDataDefine setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MemoryDataDefine setDescription(String description) {
        this.description = description;
        return this;
    }

    public Object getReference() {
        return reference;
    }

    public MemoryDataDefine setReference(Object reference) {
        this.reference = reference;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryDataDefine)) return false;
        MemoryDataDefine that = (MemoryDataDefine) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
