package com.ohayoyo.gateway.define;

import java.util.Map;

public interface ReferenceObjectsDefine extends ReferenceDefine, NameDefine {

    Map<String, ReferenceDefine> getReferences();

    ReferenceDefine setReferences(Map<String, ReferenceDefine> references);

}
