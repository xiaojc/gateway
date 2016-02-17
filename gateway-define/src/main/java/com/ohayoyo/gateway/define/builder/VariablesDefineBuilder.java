package com.ohayoyo.gateway.define.builder;

import com.ohayoyo.gateway.define.core.VariablesDefine;

/**
 * @author 蓝明乐
 */
public abstract class VariablesDefineBuilder extends AbstractParametersDefineBuilder<VariablesDefine, PathDefineBuilder> {

    protected VariablesDefineBuilder(PathDefineBuilder referenceBuilder) {
        super(referenceBuilder);
    }

}
