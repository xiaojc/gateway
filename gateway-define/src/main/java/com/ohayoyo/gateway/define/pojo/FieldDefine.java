package com.ohayoyo.gateway.define.pojo;


import com.ohayoyo.gateway.define.ObjectDefine;
import com.ohayoyo.gateway.define.TypeDefine;

public interface FieldDefine extends ObjectDefine {

    String getName();

    FieldDefine setName(String name);

    String getComment();

    FieldDefine setComment(String comment);

    String getOverview();

    FieldDefine setOverview(String overview);

    String getDescription();

    FieldDefine setDescription(String description);

    TypeDefine getDataType();

    FieldDefine setDataType(TypeDefine dataType);

    Integer getDataLength();

    FieldDefine setDataLength(Integer dataLength);

    Boolean getNullable();

    FieldDefine setNullable(Boolean nullable);

    Object getDefaultValue();

    FieldDefine setDefaultValue(Object defaultValue);

}
