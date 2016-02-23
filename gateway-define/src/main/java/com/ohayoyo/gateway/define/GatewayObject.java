package com.ohayoyo.gateway.define;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public interface GatewayObject<Field extends GatewayField> {

    Set<Field> getFields();

    void setFields(Set<Field> fields);

}
