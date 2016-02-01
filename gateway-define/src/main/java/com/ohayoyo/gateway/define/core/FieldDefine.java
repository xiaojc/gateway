package com.ohayoyo.gateway.define.core;

import java.io.Serializable;

/**
 * 字段定义
 */
public interface FieldDefine extends Serializable {

    //基本数据类型

    String BYTE = "byte";

    String SHOT = "shot";

    String INT = "int";

    String LONG = "long";

    String FLOAT = "float";

    String DOUBLE = "double";

    String CHAR = "char";

    String BOOLEAN = "boolean";

    //对象数据类型

    String STRING = "string";

    String FILE = "file";

    //集合类型

    String COLLECTION = "collection"; //collection<user>

    String MAP = "map"; //map<object,object>

    String getName();

    FieldDefine setName(String name);

    String getType();

    FieldDefine setType(String type);

    Boolean getRequired();

    FieldDefine setRequired(Boolean required);

    Integer getLength();

    FieldDefine setLength(Integer length);

    Object getDefaultValue();

    FieldDefine setDefaultValue(Object defaultValue);

    String getDescription();

    FieldDefine setDescription(String description);

    Object getReference();

    FieldDefine setReference(Object reference);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
