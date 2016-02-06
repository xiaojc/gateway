package com.ohayoyo.gateway.define;

import java.io.Serializable;
import java.util.Map;

public interface ReferenceDefine extends Serializable {

    String getReference();

    ReferenceDefine setReference(String reference);

    Map<String, Object> getMapping();

    ReferenceDefine setMapping(Map<String, Object> mapping);

}
