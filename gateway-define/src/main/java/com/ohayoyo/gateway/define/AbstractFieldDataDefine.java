package com.ohayoyo.gateway.define;

public abstract class AbstractFieldDataDefine implements FieldDataDefine {

    private String name;

    private String overview;

    private String comment;

    private String description;

    private String dataType;

    private Integer dataLength = -1;

    private Boolean nullable = true;

    private Object defaultValue;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public AbstractFieldDataDefine setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public AbstractFieldDataDefine setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public AbstractFieldDataDefine setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public AbstractFieldDataDefine setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String getDataType() {
        return dataType;
    }

    @Override
    public AbstractFieldDataDefine setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    @Override
    public Integer getDataLength() {
        return dataLength;
    }

    @Override
    public AbstractFieldDataDefine setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
        return this;
    }

    @Override
    public Boolean getNullable() {
        return nullable;
    }

    @Override
    public AbstractFieldDataDefine setNullable(Boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    @Override
    public AbstractFieldDataDefine setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

}
