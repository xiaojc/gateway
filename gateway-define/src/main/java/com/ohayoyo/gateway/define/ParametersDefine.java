package com.ohayoyo.gateway.define;

import java.util.Map;
import java.util.Set;

public interface ParametersDefine extends ReferenceDefine<ParametersDefine> {

    String PARAMETER_NAME_VARIABLES = "VARIABLES";

    String PARAMETER_NAME_QUERIES = "QUERIES";

    String PARAMETER_NAME_HEADERS = "HEADERS";

    @Override
    String getName();

    @Override
    ParametersDefine setName(String name);

    @Override
    Set<FieldDefine> getFields();

    @Override
    ParametersDefine setFields(Set<FieldDefine> fields);

    @Override
    Map<String, ParametersDefine> getReferences();

    @Override
    ParametersDefine setReferences(Map<String, ParametersDefine> references);

}
