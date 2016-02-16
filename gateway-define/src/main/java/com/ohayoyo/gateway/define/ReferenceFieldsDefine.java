package com.ohayoyo.gateway.define;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public interface ReferenceFieldsDefine extends ReferenceObjectsDefine ,NameDefine {

    Set<FieldDefine> getFields();

    ReferenceFieldsDefine setFields(Set<FieldDefine> fields);

}
