package com.ohayoyo.gateway.define;

/**
 * @author 蓝明乐
 */
public class ReferenceFieldDefine implements FieldDefine {

    private String name;

    private String comment;

    private String overview;

    private String description;

    private String dataType;

    private Integer dataLength = -1;

    private Boolean nullable;

    private Object defaultValue;

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public FieldDefine setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public FieldDefine setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public FieldDefine setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public FieldDefine setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String getDataType() {
        return dataType;
    }

    @Override
    public FieldDefine setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    @Override
    public Integer getDataLength() {
        return dataLength;
    }

    @Override
    public FieldDefine setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
        return this;
    }

    @Override
    public Boolean getNullable() {
        return nullable;
    }

    @Override
    public FieldDefine setNullable(Boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    @Override
    public FieldDefine setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReferenceFieldDefine)) return false;
        ReferenceFieldDefine that = (ReferenceFieldDefine) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
