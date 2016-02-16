package com.ohayoyo.gateway.define;

import java.util.Map;

/**
 * @author 蓝明乐
 */
public interface ReferenceObjectsDefine extends ReferenceDefine, NameDefine {

    Map<String, ReferenceDefine> getReferences();

    ReferenceDefine setReferences(Map<String, ReferenceDefine> references);

}
