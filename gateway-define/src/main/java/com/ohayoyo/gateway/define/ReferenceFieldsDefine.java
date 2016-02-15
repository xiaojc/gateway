package com.ohayoyo.gateway.define;

import java.util.Set;

public interface ReferenceFieldsDefine extends ReferenceObjectsDefine ,NameDefine {

    Set<FieldDefine> getFields();

    ReferenceFieldsDefine setFields(Set<FieldDefine> fields);

}
