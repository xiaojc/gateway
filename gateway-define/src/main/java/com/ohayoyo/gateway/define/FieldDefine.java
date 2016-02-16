package com.ohayoyo.gateway.define;

/**
 * @author 蓝明乐
 */
public interface FieldDefine extends NameDefine {

    String getComment();

    FieldDefine setComment(String comment);

    @Override
    String getName();

    @Override
    FieldDefine setName(String name);

    String getOverview();

    FieldDefine setOverview(String overview);

    String getDescription();

    FieldDefine setDescription(String description);

    String getDataType();

    FieldDefine setDataType(String dataType);

    Integer getDataLength();

    FieldDefine setDataLength(Integer dataLength);

    Boolean getNullable();

    FieldDefine setNullable(Boolean nullable);

    Object getDefaultValue();

    FieldDefine setDefaultValue(Object defaultValue);

}
