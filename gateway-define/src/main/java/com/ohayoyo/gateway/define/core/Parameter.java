package com.ohayoyo.gateway.define.core;

/**
 * @author 蓝明乐
 */
public class Parameter implements ObjectDefine {

    //primitive -> form & data

    String DATA_TYPE_BYTE = "byte";

    String DATA_TYPE_SHORT = "short";

    String DATA_TYPE_INT = "int";

    String DATA_TYPE_LONG = "long";

    String DATA_TYPE_CHAR = "char";

    String DATA_TYPE_FLOAT = "float";

    String DATA_TYPE_DOUBLE = "double";

    String DATA_TYPE_BOOLEAN = "boolean";

    // object -> form & data

    String DATA_TYPE_STRING = "string";

    //file -> form

    String DATA_TYPE_FILE = "file";

    //collection -> form & data

    String DATA_TYPE_LIST = "list";

    String DATA_TYPE_SET = "set";

    //map -> form & data

    String DATA_TYPE_MAP = "map";

    private String name;

    private String comment;

    private String overview;

    private String description;

    private String dataType;

    private Integer dataLength = -1;

    private Boolean nullable = true;

    private Object defaultValue;

    public String getComment() {
        return comment;
    }

    public Parameter setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getName() {
        return name;
    }

    public Parameter setName(String name) {
        this.name = name;
        return this;
    }

    public String getOverview() {
        return overview;
    }

    public Parameter setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Parameter setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDataType() {
        return dataType;
    }

    public Parameter setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public Parameter setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
        return this;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public Parameter setNullable(Boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public Parameter setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parameter)) return false;
        Parameter that = (Parameter) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
