package com.ohayoyo.gateway.define.pojo;

import com.ohayoyo.gateway.define.TypeDefine;

public class DefaultFieldDefine implements FieldDefine {

    private String name;

    private String comment;

    private String overview;

    private String description;

    private TypeDefine dataType;

    private Integer dataLength;

    private Boolean nullable;

    private Object defaultValue;

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public DefaultFieldDefine setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DefaultFieldDefine setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public DefaultFieldDefine setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public DefaultFieldDefine setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public TypeDefine getDataType() {
        return dataType;
    }

    @Override
    public DefaultFieldDefine setDataType(TypeDefine dataType) {
        this.dataType = dataType;
        return this;
    }

    @Override
    public Integer getDataLength() {
        return dataLength;
    }

    @Override
    public DefaultFieldDefine setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
        return this;
    }

    @Override
    public Boolean getNullable() {
        return nullable;
    }

    @Override
    public DefaultFieldDefine setNullable(Boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    @Override
    public DefaultFieldDefine setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultFieldDefine)) return false;
        DefaultFieldDefine that = (DefaultFieldDefine) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
