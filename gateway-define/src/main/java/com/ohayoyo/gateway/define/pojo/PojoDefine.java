package com.ohayoyo.gateway.define.pojo;

import com.ohayoyo.gateway.define.ObjectDefine;

import java.util.Map;
import java.util.Set;

public interface PojoDefine extends ObjectDefine {

    String getName();

    PojoDefine setName(String name);

    Set<FieldDefine> getFields();

    PojoDefine setFields(Set<FieldDefine> fields);

    Map<String, PojoDefine> getReferences();

    PojoDefine setReferences(Map<String, PojoDefine> references);

}
