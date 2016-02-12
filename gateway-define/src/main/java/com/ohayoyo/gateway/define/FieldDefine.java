package com.ohayoyo.gateway.define;

public class FieldDefine implements NameDefine {

    private String name;

    private String comment;

    private String overview;

    private String description;

    private String dataType;

    private Integer dataLength = -1 ;

    private Boolean nullable;

    private Object defaultValue;

    public String getComment() {
        return comment;
    }

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

    public String getOverview() {
        return overview;
    }

    public FieldDefine setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FieldDefine setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDataType() {
        return dataType;
    }

    public FieldDefine setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public FieldDefine setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
        return this;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public FieldDefine setNullable(Boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public FieldDefine setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FieldDefine)) return false;
        FieldDefine that = (FieldDefine) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
