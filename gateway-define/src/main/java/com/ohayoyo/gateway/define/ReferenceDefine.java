package com.ohayoyo.gateway.define;

import java.util.Map;
import java.util.Set;

public interface ReferenceDefine<Define extends ReferenceDefine> extends NameDefine {

    @Override
    String getName();

    @Override
    Define setName(String name);

    Set<FieldDefine> getFields();

    Define setFields(Set<FieldDefine> fields);

    Map<String, Define> getReferences();

    Define setReferences(Map<String, Define> references);

}
