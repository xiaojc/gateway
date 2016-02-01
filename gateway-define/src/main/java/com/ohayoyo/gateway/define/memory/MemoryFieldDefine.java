package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.core.FieldDefine;

public class MemoryFieldDefine implements FieldDefine {

    private String name;

    private String type;

    private Boolean required;

    private Integer length;

    private Object defaultValue;

    private String description;

    private Object reference;

    public MemoryFieldDefine() {
    }

    public String getName() {
        return name;
    }

    public MemoryFieldDefine setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public MemoryFieldDefine setType(String type) {
        this.type = type;
        return this;
    }

    public Boolean getRequired() {
        return required;
    }

    public MemoryFieldDefine setRequired(Boolean required) {
        this.required = required;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public MemoryFieldDefine setLength(Integer length) {
        this.length = length;
        return this;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public MemoryFieldDefine setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MemoryFieldDefine setDescription(String description) {
        this.description = description;
        return this;
    }

    public Object getReference() {
        return reference;
    }

    public MemoryFieldDefine setReference(Object reference) {
        this.reference = reference;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryFieldDefine)) return false;
        MemoryFieldDefine that = (MemoryFieldDefine) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
