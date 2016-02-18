package com.ohayoyo.gateway.define.builder;

import com.ohayoyo.gateway.define.http.HeadersDefine;

/**
 * @author 蓝明乐
 */
public abstract class HeadersDefineBuilder<ThenDefineBuilder extends DefineBuilder> extends AbstractParametersDefineBuilder<HeadersDefine, ThenDefineBuilder> {

    protected HeadersDefineBuilder(ThenDefineBuilder thenDefineBuilder) {
        super(thenDefineBuilder);
    }

}
