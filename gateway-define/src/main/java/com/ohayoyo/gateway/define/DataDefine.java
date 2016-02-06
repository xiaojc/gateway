package com.ohayoyo.gateway.define;

import java.io.Serializable;

public interface DataDefine extends Serializable {

    String BYTE = "byte";

    String SHOT = "shot";

    String INT = "int";

    String LONG = "long";

    String FLOAT = "float";

    String DOUBLE = "double";

    String CHAR = "char";

    String BOOLEAN = "boolean";

    String STRING = "string";

    String FILE = "file";

    String OBJECT = "object";

    //暂不支持
    String COLLECTION = "collection"; //collection<user> 支持泛型,默认泛型object

    //暂不支持
    String LIST = "list";//list<object> 支持泛型,默认泛型object

    //暂不支持
    String SET = "set"; //set<object> 支持泛型,默认泛型object

    //暂不支持
    String MAP = "map"; //map<object,object> 支持泛型,默认泛型object

    String getName();

    DataDefine setName(String name);

    String getType();

    DataDefine setType(String type);

    Boolean getRequired();

    DataDefine setRequired(Boolean required);

    Integer getLength();

    DataDefine setLength(Integer length);

    Object getDefaultValue();

    DataDefine setDefaultValue(Object defaultValue);

    String getDescription();

    DataDefine setDescription(String description);

    ReferenceDefine getReference();

    DataDefine setReference(ReferenceDefine reference);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
