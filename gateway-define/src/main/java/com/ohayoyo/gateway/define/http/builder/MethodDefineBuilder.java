package com.ohayoyo.gateway.define.http.builder;

import com.ohayoyo.gateway.define.http.MethodDefine;

/**
 * @author 蓝明乐
 */
public abstract class MethodDefineBuilder extends AbstractNameScopesDefineBuilder<MethodDefine, RequestDefineBuilder> {

    protected MethodDefineBuilder(RequestDefineBuilder requestDefineBuilder) {
        super(requestDefineBuilder);
    }

}
