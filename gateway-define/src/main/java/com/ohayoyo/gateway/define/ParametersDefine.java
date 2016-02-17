package com.ohayoyo.gateway.define;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public interface ParametersDefine extends ObjectDefine {

    Set<ParameterDefine> getFields();

    ParametersDefine setFields(Set<ParameterDefine> fields);

}
