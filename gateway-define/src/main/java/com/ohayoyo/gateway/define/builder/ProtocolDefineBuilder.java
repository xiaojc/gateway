package com.ohayoyo.gateway.define.builder;

import com.ohayoyo.gateway.define.http.ProtocolDefine;

/**
 * @author 蓝明乐
 */
public abstract class ProtocolDefineBuilder extends AbstractNameScopesDefineBuilder<ProtocolDefine, RequestDefineBuilder> {

    protected ProtocolDefineBuilder(RequestDefineBuilder requestDefineBuilder) {
        super(requestDefineBuilder);
    }

}
