package com.ohayoyo.gateway.define.core;

import java.io.Serializable;

public interface DataDefine extends Serializable {

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

    Object getReference();

    DataDefine setReference(Object reference);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
