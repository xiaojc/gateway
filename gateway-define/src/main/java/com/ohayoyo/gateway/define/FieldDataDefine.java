package com.ohayoyo.gateway.define;

import java.io.Serializable;

public interface FieldDataDefine extends Serializable {

    String SUPPORT_DATA_TYPE_NUMBER = "number";

    String SUPPORT_DATA_TYPE_BOOLEAN = "boolean";

    String SUPPORT_DATA_TYPE_STRING = "string";

    String SUPPORT_DATA_TYPE_NUMBER_TWO_DIMENSION_ARRAY = "number[]";

    String SUPPORT_DATA_TYPE_STRING_TWO_DIMENSION_ARRAY = "string[]";

    String getName();

    FieldDataDefine setName(String name);

    String getOverview();

    FieldDataDefine setOverview(String overview);

    String getComment();

    FieldDataDefine setComment(String comment);

    String getDescription();

    FieldDataDefine setDescription(String description);

    String getDataType();

    FieldDataDefine setDataType(String dataType);

    Integer getDataLength();

    FieldDataDefine setDataLength(Integer dataLength);

    Boolean getNullable();

    FieldDataDefine setNullable(Boolean isNullable);

    Object getDefaultValue();

    FieldDataDefine setDefaultValue(Object defaultValue);

}
