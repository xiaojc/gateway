package com.ohayoyo.gateway.define;

public interface GatewayField {

    String getName();

    void setName(String name);

    String getOverview();

    void setOverview(String overview);

    String getComment();

    void setComment(String comment);

    String getDescription();

    void setDescription(String description);

    String getType();

    void setType(String type);

    Integer getLength();

    void setLength(Integer length);

    Boolean getNullable();

    void setNullable(Boolean nullable);

    Object getDefaultValue();

    void setDefaultValue(Object defaultValue);

}
