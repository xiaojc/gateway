package com.ohayoyo.gateway.define.builder;

import com.ohayoyo.gateway.define.core.MethodDefine;

/**
 * @author 蓝明乐
 */
public abstract class MethodDefineBuilder extends AbstractNameScopesDefineBuilder<MethodDefine, RequestDefineBuilder> {

    protected MethodDefineBuilder(RequestDefineBuilder requestDefineBuilder) {
        super(requestDefineBuilder);
    }

}
