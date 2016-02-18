package com.ohayoyo.gateway.define.core;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public interface ParametersDefine extends ObjectDefine {

    Set<Parameter> getParameters();

    ParametersDefine setParameters(Set<Parameter> parameters);

}
